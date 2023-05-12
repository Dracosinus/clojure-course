(ns tp2)

(require '[clj-time.core :as t])
;(t/date-time 1986)

(def bibliotheque (atom {}))
(def emprunts (atom {}))

;1
(defrecord Book [titre auteur editeur
date-de-publication isbn exemplaires-disponibles])

(defn create-book [titre auteur editeur
date-de-publication isbn]
  (swap! bibliotheque assoc isbn (->Book titre auteur editeur
date-de-publication isbn 1))
)

(create-book "harry potter a l'école des sorciers" "JK Rowling"
             "Gallimard jeunesse" (t/date-time 1997 06 26) "2070584623")
(create-book "harry potter et la chambre des secrets" "JK Rowling"
             "Gallimard jeunesse" (t/date-time 1997 07 02) "2070584624")
(create-book "L'appel de Cthulhu" "HP Lovecraft" "POINTS" (t/date-time 1928 02) "2757851357")


(defn list-books-by-author [author]
  (filter #(= author (:auteur %)) (vals @bibliotheque)))

(list-books-by-author "JK Rowling")

(defn sort-books-by-date []
  (sort-by :date-de-publication (vals @bibliotheque))
)

(sort-books-by-date)

(defn get-book-isbn [book] 
  (:isbn book))

(get-book-isbn (get @bibliotheque "2070584623"))


(defn set-book-isbn [book isbn] 
  (swap! bibliotheque dissoc (:isbn book))
  (swap! bibliotheque assoc isbn (assoc book :isbn isbn))
  )

(get-book-isbn (set-book-isbn (get @bibliotheque "2070584623") 1234))

(defn print-book [book]
  (let [{titre :titre auteur :auteur editeur :editeur date-de-publication :date-de-publication isbn :isbn exemplaires-disponibles :exemplaires-disponibles} book]
    (print titre)
    (print auteur)
    (print editeur)
    (print date-de-publication)
    (print isbn)
    (print exemplaires-disponibles)
    )
)

(print-book (get @bibliotheque "2070584623"))

(defrecord Borrower [nom prenom addresse livres-empruntes])

(defn create-borrower[nom prenom addresse]
  (swap! emprunts assoc (hash (str nom prenom)) (->Borrower nom prenom addresse []))
)

(create-borrower "Benoit" "Schuler" "FAR")

(defn borrow-book [borrower-key isbn]
  (if-let [target-book (find @bibliotheque isbn)]
    (if (> (:exemplaires-disponibles target-book) 0) 
      (if-let [borrower (find @emprunts borrower-key)]
        (do (swap! bibliotheque assoc isbn (assoc target-book :exemplaires-disponibles (- exemplaires-disponibles 1)))
            (swap! emprunts assoc borrower-key (assoc borrower :livres-empruntes (conj livres-empruntes target-book)))
        ) )
      )
    )
  )


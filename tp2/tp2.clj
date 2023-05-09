(ns tp2)

(require '[clj-time.core :as t])
;(t/date-time 1986)

;1
(defrecord Book [titre auteur éditeur
date-de-publication isbn exemplaires-disponibles])

(defn create-book [titre auteur éditeur
date-de-publication isbn]
  (->Book titre auteur éditeur
date-de-publication isbn 1))

(def hp1 (create-book "harry potter a l'école des sorciers" "JK Rowling"
             "Gallimard jeunesse" (t/date-time 1997 06 26) 2070584623))
(def hp2 (create-book "harry potter a l'école des sorciers" "JK Rowling"
                      "Gallimard jeunesse" (t/date-time 1997 07 02) 2070584623))
(def cthulhu (create-book "L'appel de Cthulhu" "HP Lovecraft" "POINTS" (t/date-time 1928 02) 2757851357))

(def test-book-list [hp1 cthulhu hp2])

(defn list-books-by-author [books author]
  (filter #(= author (:auteur %)) books))

(list-books-by-author test-book-list "JK Rowling")

(defn sort-books-by-date [books]
  (sort-by :date-de-publication books)
)

(sort-books-by-date test-book-list)

(defn get-book-isbn [book] 
  (:isbn book))

(get-book-isbn cthulhu)

(defn set-book-isbn [book isbn]
  (assoc book :isbn isbn))

(get-book-isbn (set-book-isbn cthulhu 1234))

(defn print-book [book]
  (print book))

(print-book cthulhu)

(defrecord Borrower [nom prenom addresse livres-empruntes])

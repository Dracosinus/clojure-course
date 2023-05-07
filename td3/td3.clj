(ns td3)

(defn tsd[l]
  (butlast l))

(tsd '(a b c d e f))


; ex 9
(defn pyra [n]
  (defn aux [n f]
    (dotimes [x (+ f 1)] (print f))
    (println)
    (if (not= n f) 
      (aux n (+ f 1))
      )
    )
  (aux n 0)
  ) 


(pyra 3)
(pyra 5)

;((pyra 3))


; ex 10
(defn pyra2 [n]
  (defn aux [n f]
    (dotimes [x (- n f)] (print " "))
    (dotimes [x (+ f 1)] (print (- n f)))
    (println)
    (if (not= n f)
      (aux n (+ f 1)))) 
  (aux n 0))

(pyra2 5)

; brouillon
 (def legumes #{"tomate" "artichaut" "concombre"})


; ex 11
(defrecord Person [nom prenom telephone])

(defn creer-personne [nom prenom telephone]
  (map->Person {:nom nom :prenom prenom :telephone telephone}))



(defn creer-personne-clavier []
  (let [nom (read-line)
        prenom (read-line)
        telephone (read-line)]
    (creer-personne nom prenom telephone)))
 

(defn afficher-personne [personne]
  (printf "Nom: %s\nPrenom: %s\nTelephone: %s\n"
          (:nom personne)
          (:prenom personne)
          (:telephone personne)))


(defn comparer-personnes [personne1 personne2]
  (let [nom-prenom1 (str (:nom personne1) (:prenom personne1))
        nom-prenom2 (str (:nom personne2) (:prenom personne2))]
    (compare nom-prenom1 nom-prenom2)))


(defn ajouter-personne [annuaire personne]
  (assoc annuaire (str (str (:nom personne) " ") (:prenom personne)) personne))

(defn afficher-annuaire [annuaire]
  (doseq [[k v] annuaire]
    (print k)
    (print ": \n")
    (afficher-personne v)
    (println)
    )
  )

(defn supprimer-personne [annuaire personne]
  (def key (str (str (:nom personne) " ") (:prenom personne)))
  (if (contains? annuaire key)
    (dissoc annuaire key)
    (println (str key " not found in annuaire, no changes")))
  )

(defn chercher-personne [annuaire nom prenom]
  (def mykey  (str (str nom " ") prenom))
  (if (contains? annuaire mykey) (get annuaire mykey) 
      (println (str mykey " not found in annuaire, can't return person")))
  )

(defn chercher-personne2 [annuaire personne]
  (def mykey (str (str (:nom personne) " ") (:prenom personne))) 
  (get annuaire mykey)
)

(defn filter-letter [annuaire letter]
  (keep (if (= (first (annuaire %)) \letter) [% (annuaire %)]) (keys annuaire))
)

(filter-letter annuaire-test2 "p")


; testing 
(def potter (creer-personne "potter" "harry" 1234))
(def granger (creer-personne "granger" "hermione" 2222))
(def weasley (creer-personne "weasley" "ron" 1111))
(def rogue (creer-personne-clavier))
 (creer-personne-clavier)
(afficher-personne (creer-personne "potter" "harry" 1234))
(comparer-personnes (creer-personne "potter" "harry" 1234)
                    (creer-personne "potter" "hermione" 151756))
(comparer-personnes rogue weasley)

(def annuaire-test {"rogue severus" rogue, "potter harry" potter})
(def annuaire-test2 {"rogue severus" rogue, "potter harry" potter, "potter lily" (creer-personne "potter" "lily" 5555)})


(assoc annuaire-test "weasley ronald" weasley)
(assoc annuaire-test "granger ronald" weasley)

(ajouter-personne annuaire-test granger)
(afficher-annuaire annuaire-test)

(supprimer-personne annuaire-test granger)
(supprimer-personne annuaire-test rogue)

(def malfoy (map->Person {:nom "malfoy" :prenom "draco" :number 44}))
(:prenom malfoy)

(chercher-personne annuaire-test (str "granger") (str "hermione"))
(chercher-personne annuaire-test "rogue" "severus")
(chercher-personne annuaire-test "dobby" "elf")
(chercher-personne2 annuaire-test granger)
(chercher-personne2 annuaire-test rogue)

; cours test
(defrecord Acteur [nom prenom age])
 (def depardieu (map->Acteur {:prenom "Gérard" :age 70 :nom "Depardieu"}))
(:prenom depardieu)

(def data {:apple "fruit" :banana "fruit" :carrot "vegetable" :apricot "fruit"})
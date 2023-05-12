(ns hell)

(def v [1 2 3 4 5])
(get v 2)

(def plateau (vector
              (vector 0 0 0)
              (vector 0 0 0)
              (vector 0 0 0)))

(get (get plateau 1) 2)

(= 1 2 1 1 1)
(= 1 1 1 2)

(defn make-morpion []
  (vector
   (vector 0 0 0)
   (vector 0 0 0)
   (vector 0 0 0)))

(defn print-morpion [plateau]
  (let [hr-sep "----------------"]
    (println hr-sep)
    (doseq [i (reverse (range 3))]
      (doseq [j (range 3)]
        (print "|" (cond
                     (= (get-in plateau [i j]) 1) " X "
                     (= (get-in plateau [i j]) 2) " O "
                     :else "   ")))
      (println "|")
      (println hr-sep))))

(defn set-case-morpion [plateau player i j]
  (assoc-in plateau [i j] player))


; test de la fonction :
(print-morpion (set-case-morpion (make-morpion) 1 1 1))
(print-morpion (set-case-morpion (make-morpion) 2 2 2))
(print-morpion (set-case-morpion (make-morpion) 2 1 2))
(print-morpion (set-case-morpion (set-case-morpion (make-morpion) 2 2 2) 1 1 1))

; -------------------------------------------

; -------------------------------------------
; renvoie true si le coup est legal (case (i,j) valide et vide
(defn legal-move-morpion? [plateau i j]
  (= (get-case-morpion plateau i j) 0))


; test de la fonction :
(legal-move-morpion? (make-morpion) 1 1)
(legal-move-morpion? (make-morpion) 3 3)
(legal-move-morpion? (set-case-morpion (make-morpion) 1 1 1) 1 1)


(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (dec n)) (fib (- n 2)))))

 (time (fib 35))


(def m (atom {}))

(defn fibo [n]
  (if-let [e (find @m n)]
    (val e)
    (let [ret (if (<= n 1)
                n
                (+ (fibo (dec n)) (fibo (- n 2))))]
      (swap! m assoc n ret)
      ret)))

(time (fibo 35))


'(1 2 3)
(apply list [1 2 3])
(conj [1 2 3] nil)
(into [1 2 3] [4 5 6])
(into [1 2 3] [])
(conj '(1 2 3) nil)
(reverse '(1 2 3))
(reverse (conj (reverse '(1 2 3)) 4))
(remove #{3 5} [1 2 3 4 5 6 7 8 9])
(remove #{3} ["a" "b" "c" "d" 3])

(defn somme[list]
  (reduce + list))

(somme [1 2 3 4 5])

(defn plus-grand[list]
  (reduce (fn [x y]
            (if (> x y) x y)) 0 list))

(plus-grand [1 6 7 4 5])

(defn compter-pairs[list]
  (count (filter even? list)))

(compter-pairs [1 2 3 4 5 6 7 8 9 10])

(defn tous-positifs?[list]
  (= (count (take-while #(>= % 0) list)) (count list)))

(tous-positifs? [1 2 3 4 5 0 -2])
; Renvoie true
(tous-positifs? [1 2 3 -4 5])
; Renvoie false (il y a un élément négatif)

(defn concatener[list1 list2]
  (concat list1 list2)
  )

(concatener [1 2 3] [4 5 6]) ; Renvoie [1 2 3 4 5 6]

(concat [1 2 3] [4 5 6])

(defn verifier-cle[map key]
  (contains? map key))

(verifier-cle {:a 1 :b 2 :c 3} :b) ; Renvoie true
(verifier-cle {:a 1 :b 2 :c 3} :d) ; Renvoie false

(defn valeurs-superieures [map limit]
  (into [](filter #(> % limit) (vals map))))

(valeurs-superieures {"Alice" 15 "Bob" 12 "Eve" 18} 14) ; Renvoie [15 18]
(vals {"Alice" 15 "Bob" 12 "Eve" 18})

(defn moyenne-notes [map]
  (/ (reduce + (into [](vals map))) (count map)))

(moyenne-notes {"Alice" 15 "Bob" 12 "Eve" 18}) ; Renvoie 15
(count  {"Alice" 15 "Bob" 12 "Eve" 18})
(vals  {"Alice" 15 "Bob" 12 "Eve" 18})
(into [](vals  {"Alice" 15 "Bob" 12 "Eve" 18}))
  
(/ 2 4)

(defrecord Personne [nom age profession])
(defn creer-personne[nom age profession]
  (->Personne nom age profession))
(creer-personne "Alice" 25 "Ingénieur")
; Renvoie une instance de Personne avec les valeurs "Alice", 25 et "Ingénieur"


(def alice (Personne. "Alice" 25 "Ingénieur"))
(def bob (Personne. "Bob" 30 "Ingénieur"))

(defn comparer-personnes[p1 p2]
  (and (= (:nom p1 ) (:nom p2)) 
       (= (:age p1) (:age p2)) 
       (= (:profession p1) (:profession p2))))


(comparer-personnes alice alice) ; Renvoie true
(comparer-personnes alice bob) ; Renvoie false

(defn somme-chiffres[number]
  (reduce + (map #(Character/getNumericValue %) (str number))))

(somme-chiffres 12345) ; Renvoie 15 (1 + 2 + 3 + 4 + 5 = 15)

(map #(Character/getNumericValue %) (str 12345))

(defn est-anagramme?[word1 word2]
  (= (frequencies word1) (frequencies word2))
)
(est-anagramme? "listen" "silent") ; Renvoie true
(est-anagramme? "hello" "world") ; Renvoie false

(frequencies "silent")
(seq "silent")
(= (frequencies "silent") (frequencies "listen"))
(map #(Character/getDirectionality %) "silent")


(defn compter-mots-frequents[map]
  (frequencies map))
(compter-mots-frequents ["apple" "banana" "apple" "orange" "banana" "apple"]) 
; Renvoie {"apple" 3, "banana" 2, "orange" 1}

(frequencies ["apple" "banana" "apple" "orange" "banana" "apple"])

(defn verifier-parentheses[string]
  (= (get (frequencies string) \() (get (frequencies string) \))))

(verifier-parentheses "((()))") ; Renvoie true
(verifier-parentheses "(()())") ; Renvoie true
(verifier-parentheses "((())") ; Renvoie false

(defn supprimer-doublons[list]
  (into [](distinct list)))

(supprimer-doublons [1 2 3 4 2 3 5]) ; Renvoie [1 2 3 4 5]

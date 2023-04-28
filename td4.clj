(ns td4)

(defrecord Node [value left right])

(defn a-vide []
  (->Node nil nil nil))


(a-vide)

(defn a-noeud [v sag sad]
  (->Node v sag sad))

(def tree0 (a-noeud 0 (a-vide) (a-vide)))

(defn a-vide? [A]
  (let [{val :value sag :left sad :right} A]
    (and (nil? val) (nil? sag) (nil? sad))))

(a-vide? (a-vide))
(a-vide? tree0)

(defn a-valeur [A]
  (let [{value :value} A]
    value))

(a-valeur (a-noeud 0 (a-vide) (a-vide)))
(a-valeur (a-vide))

(defn a-gauche [{:keys [left]}]
  left)

(a-gauche (a-vide))
(a-gauche (a-noeud 0 tree0 (a-vide)))

(defn a-right [node]
  (:right node))

(defn a-print [A]
  (defn a-print-depth [A depth]
    (cond
      (a-vide? A) (println (str (apply str (repeat depth "\t")) "()"))
      :else (let [{val :value sag :left sad :right} A]
              (println (str (apply str (repeat depth "\t")) val))
              (a-print-depth sag (inc depth))
              (a-print-depth sad (inc depth)))))
  (a-print-depth A 0)
  )

(a-print (a-vide))
(a-print tree0)
(def A (a-noeud 1 (a-noeud 2 (a-vide) (a-noeud 3 (a-vide) (a-vide))) (a-noeud 4 (a-vide)
                                                                                (a-vide))))
(a-print A)

(defn a-print-inf [A]
  (defn a-print-depth [A depth]
    (cond
      (a-vide? A) (println (str (apply str (repeat depth "\t")) "()"))
      :else (let [{val :value sag :left sad :right} A] 
              (a-print-depth sag (inc depth))
              (println (str (apply str (repeat depth "\t")) val))
              (a-print-depth sad (inc depth)))))
  (a-print-depth A 0))

(a-print-inf A)

(defn a-print-suf [A]
  (defn a-print-depth [A depth]
    (cond
      (a-vide? A) (println (str (apply str (repeat depth "\t")) "()"))
      :else (let [{val :value sag :left sad :right} A]
              (a-print-depth sag (inc depth))
              (a-print-depth sad (inc depth))
              (println (str (apply str (repeat depth "\t")) val)))))
  (a-print-depth A 0))

(a-print-suf A)

(a-print-inf A)
;; '(1 2 3)
;; (apply list [1 2 3])
;; (conj [1 2 3] nil)
;; (into [1 2 3] [4 5 6])
;; (into [1 2 3] [])
;; (conj '(1 2 3) nil)
;; (reverse '(1 2 3))
;; (reverse (conj (reverse '(1 2 3)) 4))

(defn parcours-prof [A]
  (defn parcours-list [A list]
    (cond
      (a-vide? A) []
      (and (a-vide? (:left A)) (a-vide? (:right A)) ) (conj list (:value A)) 
      (a-vide? (:left A)) (parcours-list (:right A) (conj list (:value A)))
      (a-vide? (:right A)) (parcours-list (:left A) (conj list (:value A))) 
      :else (into (parcours-list (:left A) (conj list (:value A)))  (parcours-list (:right A) []))
      )
    )
  (apply list (parcours-list A [])))

(parcours-prof (a-vide))
(parcours-prof tree0)                                                                              (a-vide))))
(parcours-prof A)


(defn parcours-prof-inf
  ([A] (apply list (parcours-prof-inf A [])))
  ([A list] (cond
              (a-vide? A) []
              (and (a-vide? (:left A)) (a-vide? (:right A))) (conj list (:value A))
              (a-vide? (:left A)) (parcours-prof-inf (:right A) (conj list (:value A)))
              (a-vide? (:right A)) (parcours-prof-inf (:left A) (conj list (:value A)))
              :else (into (parcours-prof-inf (:left A) []) (parcours-list (:right A) (conj list (:value A))))
              )
  )
)


(parcours-prof-inf (a-vide))
(parcours-prof-inf tree0)                                                                              
(parcours-prof-inf A)

(defn parcours-prof-suf
  ([A] (apply list (parcours-prof-suf A [])))
  ([A list] (cond
              (a-vide? A) []
              (and (a-vide? (:left A)) (a-vide? (:right A))) (conj list (:value A))
              (a-vide? (:left A)) (parcours-prof-suf (:right A) (conj list (:value A)))
              (a-vide? (:right A)) (parcours-prof-suf (:left A) (conj list (:value A)))
              :else (into (parcours-prof-suf (:left A) []) (parcours-list (:right A) (conj list (:value A)))))))


(parcours-prof-suf (a-vide))
(parcours-prof-suf tree0)
(parcours-prof-suf A)

;; (defn parcours-largeur
;;   ([A] (apply list (parcours-prof-suf A [])))
;;   ([A list] (cond
;;               (a-vide? A) []
;;               (and (a-vide? (:left A)) (a-vide? (:right A))) (conj list (:value A))
;;               (a-vide? (:left A)) (parcours-largeur (:right A) (conj list (:value A)))
;;               (a-vide? (:right A)) (parcours-largeur (:left A) (conj list (:value A)))
;;               :else (into (parcours-largeur (:left A) []) (parcours-list (:right A) (conj list (:value A)))))))


;; (parcours-largeur (a-vide))
;; (parcours-largeur tree0)
;; (parcours-largeur A)

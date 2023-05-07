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
(a-right (a-vide))
(a-right (a-noeud 0 (a-vide) tree0))

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

;1234
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
(parcours-prof tree0)
(parcours-prof A)

;2314
(defn parcours-prof-inf
  ([A] (apply list (parcours-prof-inf A [])))
  ([A list] (cond
              (a-vide? A) []
              (and (a-vide? (:left A)) (a-vide? (:right A))) (conj list (:value A))
              (a-vide? (:left A)) (parcours-prof-inf (:right A) (conj list (:value A)))
              (a-vide? (:right A)) (parcours-prof-inf (:left A) (conj list (:value A)))
              :else (into (parcours-prof-inf (:left A) []) (parcours-prof-inf (:right A) (conj list (:value A))))
              )
  )
)

(parcours-prof-inf (a-vide))
(parcours-prof-inf tree0)
(parcours-prof-inf A)

;3241
(defn parcours-prof-suf
  ([A] (apply list (parcours-prof-suf A [])))
  ([{:keys [value left right] :as A} mylist] (cond
              (a-vide? A) []
              (and (a-vide? left) (a-vide? right)) (conj mylist value)
              (a-vide? left) (conj (parcours-prof-suf right mylist) value)
              (a-vide? right) (conj (parcours-prof-suf left mylist) value)
              :else (into (into (parcours-prof-suf left mylist) (parcours-prof-suf right)) [value])
              )))

(parcours-prof-suf (a-vide))
(parcours-prof-suf tree0)
(parcours-prof-suf A)

;1243 ou 1423
;; (defn parcours-largeur
;;   ([A] (apply list (parcours-largeur A [] [])))
;;   ([{:keys [value left right] :as A} mylist nodes-to-visit] (cond
;;                                                               (a-vide? A) (cond (empty? nodes-to-visit) mylist
;;                                                                                 :else (parcours-largeur (first nodes-to-visit) mylist (rest nodes-to-visit)))
;;                                                               (and (a-vide? left) (a-vide? right)) (cond (empty? nodes-to-visit) (conj mylist value)
;;                                                                                                          :else (parcours-largeur (first nodes-to-visit) (conj mylist value) (rest nodes-to-visit)))
;;                                                               (a-vide? left) (parcours-largeur (a-vide) (conj mylist value) (conj nodes-to-visit right))
;;                                                               (a-vide? right) (parcours-largeur (a-vide) (conj mylist value) (conj nodes-to-visit left))
;;                                                               :else (parcours-largeur (a-vide) (conj mylist value) (conj (conj nodes-to-visit left) right))
;;                                                               )
;;    )
;;   )
(defn parcours-largeur [tree]
  (let [queue (conj clojure.lang.PersistentQueue/EMPTY tree)]
    (loop [result []
           nodes-to-visit queue]
      (if (seq nodes-to-visit)
        (let [{:keys [value left right]} (first nodes-to-visit)]
          (recur (conj result value)
                 (into (rest nodes-to-visit)
                       (remove a-vide? [left right]))))
        result))))

(empty? [])
(conj [1 2 3] 4)
(first [1 2 3])
(empty? (rest (rest (rest [1 2 3]))))
[]
(apply list [1 4 2 3])

(parcours-largeur (a-vide))
(parcours-largeur tree0)
(parcours-largeur A)

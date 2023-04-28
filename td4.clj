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

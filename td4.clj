(ns td4)

(defrecord Node [value left right])

(defn a-vide []
  (map->Node {:value nil :left nil :right nil}))


(a-vide)

(defn a-noeud [v sag sad]
  (map->Node {:value v :left sag :right sad}))

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

(defn a-right [{:keys [right]}]
  right)


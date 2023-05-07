(ns guillaume)

(defn make-morpion []
  (vec (repeat 3 (vec (repeat 3 0)))))

(make-morpion)

(defn get-case-morpion [plateau i j]
  (get-in plateau [i j]))

(get-case-morpion (make-morpion) 0 0)

(get-case-morpion (make-morpion) 3 3)

(defn print-morpion [plateau]
  (dorun (map println plateau)))

(print-morpion (make-morpion))

(defn set-case-morpion [plateau player i j]
  (assoc-in plateau [i j] player))

(print-morpion (set-case-morpion (make-morpion) 1 1 1))
(print-morpion (set-case-morpion (make-morpion) 2 2 2))
(print-morpion (set-case-morpion (make-morpion) 2 1 2))

(print-morpion (set-case-morpion (make-morpion) 1 1 1))

(update-in [[0 0 0] [0 0 0] [0 0 0]] [2 0] inc)
(assoc-in [[0 0 0] [0 0 0] [0 0 0]] [2 0] 4)

(defn legal-move-morpion? [plateau i j]
  (if (== (get-case-morpion plateau i j) 0) "true" "false"))

(legal-move-morpion? (make-morpion) 0 0)
(legal-move-morpion? (make-morpion) 1 1)
(legal-move-morpion? (make-morpion) 3 3)
(legal-move-morpion? (set-case-morpion (make-morpion) 1 1 1) 1 1)

;; (defn triplette-winner? [triplette]
;;   (if (every? #{:X} triplette) :x
;;       (if (every? #{:O} triplette) :O)))

;; (defn triplettes [plateau]
;;   (concat
;;    (partition-all 3 plateau)
;;    (list
;;     (take-nth 3 plateau)
;;     (take-nth 3 (drop 1 plateau))
;;     (take-nth 3 (drop 2 plateau))
;;     (take-nth 4 plateau)
;;     (take-nth 2 (drop-last 2 (drop 2 plateau))))))

;; (triplettes (make-morpion))
;; ; -------------------------------------------

;; ; -------------------------------------------
;; ; renvoie true si une position gagnante est reconnue
;; ; alignement de 3 cases identiques et differentes de 0
;; (defn winning-morpion? [plateau]
;;   (if (first (filter #{:X :O} (map triplette-winner? (triplettes plateau)))) :true))

;; (-> (make-morpion) (set-case-morpion 1 0 0) (set-case-morpion 1 0 1) (set-case-morpion 1 0 2) (winning-morpion?))
;; (-> (make-morpion) (set-case-morpion 1 0 0) (set-case-morpion 1 0 1) (winning-morpion?))
;; (-> (make-morpion) (winning-morpion?))

(defn move-morpion [plateau player]
  (let [x (Integer/parseInt (read-line)) y (Integer/parseInt (read-line))]
    (set-case-morpion plateau player x y)))

(defn move-morpion [plateau player]
  (let [input1 (Integer/parseInt (read-line)) input2 (Integer/parseInt (read-line))]
    (if (some #{input1 input2} plateau) (println (input1))
        (println 'nope))))

(move-morpion (make-morpion) 1)

(defn winning-morpion? [plateau]
  (defn test [a b c] (and (not= a 0) (= a b) (= a c)))
  (test (get plateau "c00") (get plateau "c01") (get plateau "c02")))

(defn not-winning-morpion? [plateau]
  (defn test [a b c] (and (not= a 0) (= a b) (= a c)))
  (or (test (get plateau "c00") (get plateau "c01") (get plateau "c02"))
      (test (get plateau "c01") (get plateau "c11") (get plateau "c12"))
      (test (get plateau "c02") (get plateau "c21") (get plateau "c22"))

      (test (get plateau "c00") (get plateau "c10") (get plateau "c20"))
      (test (get plateau "c01") (get plateau "c11") (get plateau "c21"))
      (test (get plateau "c02") (get plateau "c12") (get plateau "c22"))

      (test (get plateau "c00") (get plateau "c11") (get plateau "c22"))
      (test (get plateau "c20") (get plateau "c11") (get plateau "c02"))))

(def stale-morpion
  (vector
   (vector 1 2 1)
   (vector 1 1 2)
   (vector 2 1 2)))
(get stale-morpion "c00")
(winning-morpion? stale-morpion)
(def winner-morpion
  (vector
   (vector 1 2 0)
   (vector 1 0 2)
   (vector 1 1 2)))
(winning-morpion? winner-morpion)

(defn play-morpion []
  (def plateau make-morpion)
  (def player 1)
  (while (false? (end-morpion? plateau))
    (while (false? (winning-morpion? plateau))
      (print-morpion plateau)
      (move-morpion plateau player)
      (exchange player))))
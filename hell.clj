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
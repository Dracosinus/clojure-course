#_{:clj-kondo/ignore [:namespace-name-mismatch]}
(ns td2-correction)

(defn euclide [a b]
  (if (= 0 b) a
      (euclide b (mod a b))))

(euclide 12345 765)

(defn devine
  ([] (devine (rand-int 11)))
  ([x] (if (= x (read)) (println "gagné")
           (do (println "essaye encore")
               (devine x)))))
;(devine)

(defn rond [f g]
  (fn [x] (f (g x))))

(defn add2 [x] (+ 2 x))

((rond add2 add2) 2)

(defn append [L1 L2]
  (if (empty? L1) L2
      (conj (append (rest L1) L2) (first L1))))

(append '(do re mi) '(fa sol si la re))

(defn $reverse
  ([L] ($reverse L '()))
  ([L A]
   (if (empty? L) A
       ($reverse (rest L) (conj A (first L))))))

($reverse '(1 2 3 4))

(defn list-ref [L k]
  (if (= k 0) (first L)
      (list-ref (rest L) (dec k))))

(list-ref (range 5 10) 3)

(defn fib [n]
  (if (< n 2) 1
      (+ (fib (- n 1))  (fib (- n 2)))))

(fib 8)



(defn fib-iter
  ([n] (fib-iter n 1 1))
  ([n a1 a2] (if (= n 0) a2
                 (fib-iter (dec n) (+ a1 a2) a1))))


(fib-iter 8)





(defn tri-ins [L]
  (defn insert [L x]
    (cond (empty? L)      (list x)
          (> x (first L)) (conj (insert (rest L) x) (first L))
          :else           (conj L x)))
  (cond (empty? L)        L
        (empty? (rest L)) L
        :else             (insert (tri-ins (rest L)) (first L))))

(tri-ins '(5 3 1 8 2))

(defn tri-ins-gen [L op]
  (defn insert [L x]
    (cond (empty? L) (list x)
          (op x (first L)) (conj L x)
          :else (conj (insert (rest L) x) (first L))))
  (defn tri-ins [L]
    (cond (empty? L) L
          (empty? (rest L)) L
          :else (insert (tri-ins (rest L)) (first L))))
  (tri-ins L))

(tri-ins-gen  '(5 3 1 8 2) >)

(defn tri-fusion  [L]
  (defn scission
    ([L]         (scission L [] []))
    ([L LS1 LS2] (cond (empty? L) (vector LS1 LS2)
                       :else (scission (rest L) LS2 (conj LS1 (first L))))))
  (defn fusion [L1 L2]
    (cond (empty? L1)   L2
          (empty? L2)   L1
          (< (first L1) (first L2)) (conj (fusion (rest L1) L2) (first L1))
          :else                     (conj (fusion L1 (rest L2)) (first L2))))
  (cond (empty? L)        L
        (empty? (rest L)) L
        :else             (let [R (scission L)]
                            (fusion (tri-fusion (seq (get R 0)))
                                    (tri-fusion (seq (get R 1)))))))

(tri-fusion '(5 3 1 8 2))
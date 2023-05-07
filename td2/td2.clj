(ns td2)


(defn euclide2 [a b] (if (== b 0)
                           a
                        (euclide2 b (mod a b))
                           )))

(euclide2 8 6)

(euclide2 9 12)

(euclide2 9 12)

(* 47 52)

(* 47 29)

(euclide2 2444 1363)

(rand-int 10)

;; (defn devinerec [dev] (if (= (read) dev) (print "gagne")
;;                           (devinerec dev)))

;;(defn devine [] (devinerec (rand-int 10))

(defn devinecorrection
  ([] (devinecorrection (rand-int 10)) ) 
  ([dev] (if (= (read) dev) (println "gagne")
              (devinecorrection dev)
                  )))
  
(devinecorrection)

(defn rond [f g x] (f (g x)))
  
(defn rondcorrection [f g]
  (fn [x] (f(g x))))

(defn fib[n]
  (cond
    (zero? n) 1
    (= n 1) 1
    :else (+ (fib (- n 1)) (fib (- n 2)))
  )
)

(fib 4)

(fib 15)

(defn append [l1 l2] (if (empty? l1) 
                       l2 
                       (conj (append (rest l1) l2) (first l1))))


(append '(1 2 3) '(4 5 6))
(append '(1) [1 2 3])

(conj '(1 2 3 ) 0)

(first '(1 2 3))
(count '(1 2 3))

(nth '(1 2 3) 1)

;; (defn reverse
;;   ([l] (reverse l 0))
;;   ([l index] (
;;              (cond
;;                 (>= (- (count l) 1) index) l
;;                 :else (reverse (append (subseq l 0 index)
;;                                        (conj (subseq l (+ index 1) (count l))
;;                                              (nth l index)))
;;                                (+ index 1)
;;                                )
;;                 )
;;               )
;;   )
;; )

;; (reverse '(1 2 3 4 5))

(defn reverse2 [l]
  (cond (= (count l) 0) l 
        :else (conj (reverse2 (butlast l)) (last l))
  )
)

(reverse2 '(1 2 3 4 5))


(reverse2 '(1 2 3 4 5 6))

(count '(1 2 3 4 5 6))

(butlast '(1 2 3 4 5 6))

(last '(1 2 3 4 5 6))

(conj '(1 2 3) 9))

(def my-list '(1 2 3))
; => (1 2 3)

(conj my-list 4)
; => (1 2 3 4)


(defn myfunction
  ([l] (myfunction l 0))
  ([l index] (+ index 0))
)

(subvec '(1 0 9 8 2) 1 3)

(defn list-ref [L k]
  (if (= k 0) 
    (first L) 
    (list-ref (rest L) (- k 1))))

(list-ref (range 5 10) 3)

(defn fact [n]
  (if (zero? n)
    1
    (* n (fact (dec n)))))

(fact 5)
(fact 1234)
(fact 1234N)

(defn fact-bigint [n]
  (if (zero? n)
    1
    (*' n (fact-bigint (dec' n)))))

(fact-bigint 1234)

; récursivité terminale : une fois la condition d'arrêt atteinte, il n'y a pas de calculs en attente
; récursivité enveloppée : le contraire

; exemple terminal rec
(defn fact-iter [n]
  (defn aux [n f]
    (if (zero? n)
      f
      (aux (dec' n) (*' n f)))
  )
  (aux n 1))

(defn fib [n]
  (if (< n 2) 1
      (+' (fib (- n 1)) (fib (- n 2)))
  )
)

(fib 1)
(fib 2)
(fib 3)
(fib 5)

(defn fib-loop [n]
  (loop [x n, a1 1, a2 1]
    (if [zero? x])))

(defn expt [a b]
  (cond (zero? b) 1
        :else (*' a (expt a (dec b)))
  )
)

(expt 2 10)

; les sets, elements non dupliqués 
(def legumes #{"tomate" "artichaud" "courgette"})

(conj legumes "aubergine" "salade")

(conj legumes "artichaud")

(contains? legumes "tomate")

(contains? legumes "ognions")

; tables associatives
(def notes {"alain" 10, "robert" 15, "alice" 13})

(notes["alain"])

(merge notes {"roger" 4, "lizzie" 18})


notes

(notes :alice)
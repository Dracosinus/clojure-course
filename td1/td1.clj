(ns td1)

(+ 1 1)

(defn moyenne [a b] (/ (+ a b) 2))

(moyenne 1 2)

(if (even? 1) "pair" "impair")

(defn fct1 [x y] (if (>= x y) 
                   (+ x y) 
                   (+ x (* 2 y)
                      )))

(fct1 10 1)

(fct1 2 3)

; x compris entre y et z
(defn entre [x y z] (if (and (>= x y) (<= x z)) true false))

(entre 2 1 3)

(entre 1 3 2)

(defn moy-inter [](/ (+ (read) (read)) 2))

(defn moy-inter-correct [] (moyenne (read) (read)))

(moy-inter)

(defn add2 [x] (+ x 2))

(add2 2)

(defn add4 [x] (add2 (add2 x)))

(add4 4)

(defn absfn [n] (if (>= n 0) n (- 0 n)))

(absfn 4)

(absfn -30)

(absfn 0)

(let [y 1] (+ y 1))

(def tab [\a \b \c])

tab

(get tab 1)

(get tab 6)

(vector 1 2 3)
;=
(apply vector '(1 2 3))
;=
(vec '(1 2 3))

(conj tab 1 2 3)

tab

(def l1 '(1 2 3 6 0 5/2 0))

(empty? '())

(empty? '(1 2 3))

(count tab)

(count '())

(concat l1 '(4 5 6))


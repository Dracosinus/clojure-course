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
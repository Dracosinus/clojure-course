(ns td4)

(defrecord Node [value left right])

(defn a-void []
  (->Node nil nil nil))

(defn a-node [value left right]
  (->Node value left right))

(defn a-void? [node]
  (= (:value node) (:left node) (:right node) nil))

(defn a-value [node]
  (:value node))

(defn a-left [node]
  (:left node))

(defn a-right [node]
  (:right node))

;; 1

(def node (a-node 1 (a-node 2 (a-void) (a-node 3 (a-void) (a-void))) (a-node 4 (a-void) (a-void))))

(defn a-print
  ([node] (a-print node ""))
  ([node tabs]
   (if (a-void? node)
     (println tabs "()")
     (do
       (println tabs (a-value node))
       (a-print (a-left node) (str tabs "    "))
       (a-print (a-right node) (str tabs "    "))))))

(a-print node)

(defn a-print-inf
  ([node] (a-print-inf node ""))
  ([node tabs]
   (if (a-void? node)
     (println tabs "()")
     (do
       (a-print-inf (a-left node) (str tabs "    "))
       (println tabs (a-value node))
       (a-print-inf (a-right node) (str tabs "    "))))))

(a-print-inf node)

(defn a-depth-course
  ([node] (seq (a-depth-course node [])))
  ([node list]
   (if (a-void? node)
     list
     (do
       (a-depth-course (a-right node) (a-depth-course (a-left node) (conj list (a-value node))))))))

(a-depth-course node)

(defn a-depth-course-inf
  ([node] (seq (a-depth-course-inf node [])))
  ([node list]
   (if (a-void? node)
     list
     (do
       (a-depth-course-inf (a-right node) (conj (a-depth-course-inf (a-left node) list) (a-value node)))))))

(a-depth-course-inf node)

(defn count-nodes [node]
  (if (a-void? node)
    0
    (+ (count-nodes (a-left node)) (count-nodes (a-right node)) 1)))

(count-nodes node)

(defn count-depth [node]
  (if (a-void? node)
    0
    (inc (max (count-depth (a-left node)) (count-depth (a-right node))))))

(count-depth node)

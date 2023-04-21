;; L'objectif du TP est de realiser un Morpion (tic-tac-toe)
;; en clojure a deux joueurs

;; Il est conseille de suivre le schema indique, en implementant les fonctions
;; dans cet ordre et de les tester au fur et a mesure

;; les etudiants rapides (et courageux) pourront s'inspirer 
;; de cette implementation pour faire un 'puissance 4' ... 
;; -------------------------------------------
;; Constructeur du morpion
;; On pourra utiliser le type vector
;; (a.k.o liste à la python)
;; l'une des stratégies les plus simple consiste à coder les cases :
;; - 0 : case vide
;; - 1 : joueur X
;; - 2 : joueur O


(defn make-morpion []
  (vector
   (vector 0 0 0)
   (vector 0 0 0)
   (vector 0 0 0)))

(def morpion-test make-morpion)
; test de la fonction :
(make-morpion)
; -------------------------------------------

; -------------------------------------------
; Renvoie le symbole present dans  
; la case (x,y) du plateau
; x et y sont donnes en coordonnees de 0 a 2
(defn get-case-morpion [plateau i j] 
  (get (get plateau j) i))

; test de la fonction :
(get-case-morpion (make-morpion) 1 1)
; -------------------------------------------

; -------------------------------------------
; Affiche le plateau

(defn print-morpion [plateau]
  (println (get plateau 2))
   (println (get plateau 1))
   (println (get plateau 0))
)


; test de la fonction :
(print-morpion (make-morpion))
; -------------------------------------------

; -------------------------------------------
;; Marque le coup du joueur player (represente par son numéro
;; a la position (i,j) 

(defn set-case-morpion [plateau player i j]
  (defn set-line-morpion [plateau player i j]
    (cond (= i 0) (vector player (get-case-morpion plateau 1 j) (get-case-morpion plateau 2 j))
          (= i 1) (vector (get-case-morpion plateau 0 j) player (get-case-morpion plateau 2 j))
          (= i 2) (vector (get-case-morpion plateau 0 j) (get-case-morpion plateau 1 j) player)
          :else (println "error wrong index"))
    )
  (defn line-morpion [plateau j]
    (vector (get-case-morpion plateau 0 j) (get-case-morpion plateau 1 j) (get-case-morpion plateau 2 j))
  )
  (cond (= j 0) (vector (set-line-morpion plateau player i j) (line-morpion plateau 1) (line-morpion plateau 2))
        (= j 1) (vector (line-morpion plateau 0) (set-line-morpion plateau player i j) (line-morpion plateau 2))
        (= j 2) (vector (line-morpion plateau 0) (line-morpion plateau 1) (set-line-morpion plateau player i j))
        :else (println "error wrong index"))
)
  
; test de la fonction :
(print-morpion (set-case-morpion (make-morpion) 1 1 1)) 
(print-morpion (set-case-morpion (make-morpion) 2 2 2)) 
(print-morpion (set-case-morpion (make-morpion) 2 1 2)) 
; -------------------------------------------

; -------------------------------------------
; renvoie true si le coup est legal (case (i,j) valide et vide
(defn legal-move-morpion? [plateau i j]
  (= (get-case-morpion plateau i j) 0))
        
; test de la fonction :
(legal-move-morpion? (make-morpion) 0 0)
(legal-move-morpion? (make-morpion) 1 1)
(legal-move-morpion? (make-morpion) 3 3)
(legal-move-morpion? (set-case-morpion (make-morpion) 1 1 1) 1 1)

; -------------------------------------------

; -------------------------------------------
; renvoie true si une position gagnante est reconnue
; alignement de 3 cases identiques et differentes de 0
(defn winning-morpion? [plateau]
  (or (= (get-case-morpion plateau 0 0) (get-case-morpion plateau 0 1) (get-case-morpion plateau 0 2) 1)
      (= (get-case-morpion plateau 0 0) (get-case-morpion plateau 0 1) (get-case-morpion plateau 0 2) 2)
      (= (get-case-morpion plateau 1 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 1 2) 2)
      (= (get-case-morpion plateau 1 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 1 2) 2)
      (= (get-case-morpion plateau 2 0) (get-case-morpion plateau 2 1) (get-case-morpion plateau 2 2) 2)
      (= (get-case-morpion plateau 2 0) (get-case-morpion plateau 2 1) (get-case-morpion plateau 2 2) 2)
      (= (get-case-morpion plateau 0 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 2 2) 1)
      (= (get-case-morpion plateau 0 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 2 2) 2)
      (= (get-case-morpion plateau 2 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 0 2) 1)
      (= (get-case-morpion plateau 2 0) (get-case-morpion plateau 1 1) (get-case-morpion plateau 0 2) 2) 
  )
)

  
; test de la fonction :
;(winning-morpion?   (make-morpion))  
(-> (make-morpion) (set-case-morpion 1 0 0) (set-case-morpion 1 0 1) (set-case-morpion 1 0 2) (winning-morpion?))
(-> (make-morpion) (set-case-morpion 1 0 0) (set-case-morpion 1 0 1) (winning-morpion?))
(-> (make-morpion) (winning-morpion?))



; notez l'utilisation de -> pour simuler plusieurs coups ....
; -------------------------------------------

; -------------------------------------------
; lit un coup au clavier pour le joueur player (X ou O)
; recommence si le coup n'est pas legal



(defn move-morpion [plateau player]
  (println "Enter line and column ->")
  (def line (Integer/parseInt (read-line)))
  (def column (Integer/parseInt (read-line)))
  (cond (= (legal-move-morpion? plateau line column) true) (set-case-morpion plateau player line column)
        :else (println "Illegal move please try again:"
                       (move-morpion plateau player)))
  )

(legal-move-morpion? (make-morpion) (read-line) (read-line))
(legal-move-morpion? (make-morpion) (Integer/parseInt (read-line)) (Integer/parseInt (read-line)))

; test de la fonction :
(print-morpion (move-morpion (make-morpion) 1))
(print-morpion (move-morpion (set-case-morpion (make-morpion) 2 0 2) 1))
; -------------------------------------------

; -------------------------------------------
; renvoie 1 si player = 2 et 2 si player = 1
(defn exchange [player] 
  (cond (= player 1) 2
        (= player 2) 1)
  )
; -------------------------------------------

; -------------------------------------------
; renvoie #t si toutes les cases sont occupees
; #f sinon
(defn end-morpion? [plateau] 
  (and (not= 0 (get-case-morpion plateau 0 0)) 
       (not= 0 (get-case-morpion plateau 1 0)) 
       (not= 0 (get-case-morpion plateau 2 0)) 
       (not= 0 (get-case-morpion plateau 0 1))
       (not= 0 (get-case-morpion plateau 1 1))
       (not= 0 (get-case-morpion plateau 2 1))
       (not= 0 (get-case-morpion plateau 0 2))
       (not= 0 (get-case-morpion plateau 1 2)) 
       (not= 0 (get-case-morpion plateau 2 2))
    )
  ) 

(end-morpion? (make-morpion))
(-> (make-morpion) (set-case-morpion 1 0 0) (set-case-morpion 1 0 1) (set-case-morpion 1 0 2) (end-morpion?))
(def full-morpion
  (vector
   (vector 1 1 1)
   (vector 1 2 2)
   (vector 2 2 2)))
(end-morpion? full-morpion)
; -------------------------------------------

; -------------------------------------------
; fonction principale
(defn play-morpion []
  (defn play-through-morpion [plateau player] 
    (print-morpion plateau)
    (cond (winning-morpion? plateau) (println "Congratulations, we have a winner")
          (end-morpion? plateau) (println "The game is a draw")
          :else (play-through-morpion (move-morpion plateau player) (exchange player)))
    )
  (play-through-morpion (make-morpion) 1)
 )

(play-morpion)

   
  
  

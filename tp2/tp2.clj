(ns tp2
  (:require [clj-time.core :as t]))

(t/now)

;1
(defrecord Book [titre auteur éditeur
date-de-publication isbn exemplaires-disponibles])

(defn create-book [titre auteur éditeur
date-de-publication isbn]
  (->Book titre auteur éditeur
date-de-publication isbn 1))

(def hp1 (create-book "harry potter a l'école des sorciers" "JK Rowling"
             "Gallimard jeunesse" "June 26, 1997" 2070584623))
(def hp2 (create-book "harry potter a l'école des sorciers" "JK Rowling"
                      "Gallimard jeunesse" "July 2, 1998" 2070584623))
(def cthulhu (create-book "L'appel de Cthulhu" "HP Lovecraft" "POINTS" "February 1928" 2757851357))

(def test-book-list [hp1 cthulhu hp2])

(defn list-books-by-author [books author]
  (filter #(= author (:auteur %)) books))

(list-books-by-author test-book-list "JK Rowling")

(defn sort-books-by-date [books date])
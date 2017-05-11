(ns filldays.core
  (:require [clj-time.core :as t])
  (:gen-class
   :name filldays.core))

(defn ifill
  "Returns a list of numbers between a and b"
  [a b]
  (let [ainc (inc a)]
    (cond
    (> b ainc) (concat (list ainc) (ifill ainc b))
    (< b a) (ifill b a)
    :else '()
    )
  )
)

(defn mfill
  "Returns a list with the numbers in the list-argument plus their gaps filled"
  [[a b & r]]
  (cond
   (nil? b) (list a)
   :else (concat (list a) (ifill a b) (mfill (cons b r)))
  )
)

(defn smfill
  "Sorts the list-argument before calling mfill"
  [s]
  (mfill (sort s))
  )

(defn String->Number [str]
  (let [n (read-string str)]
       (if (number? n) n nil)))

(defn -main
  [& args]
  (prn (smfill (map String->Number args))
  )
)

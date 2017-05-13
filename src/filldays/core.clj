(ns filldays.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [filldays.time-utils :refer :all])
  (:gen-class
   :name filldays.core))

(defn ifilld
  "Returns a list of dates between a and b excluded"
  [a b]
  (let [day-after-a (->> (t/days 1)
                         (t/plus a))]
    (cond
    (t/after? b day-after-a) (concat (list day-after-a) (ifilld day-after-a b))
    (t/before? b a) (ifilld b a)
    :else '()
    )
  )
)

(defn mfilld
  "Returns a list with the dates in the list-argument plus their gaps filled"
  [[a b & r]]
  (cond
   (nil? b) (list a)
   :else (->> (cons b r)
              mfilld
              (concat (list a) (ifilld a b)))
  )
)

(defn -main
  [& args]
  (->> args
       Strings->Dates
       sort
       mfilld
       Dates->Strings
       prn)
)

(ns filldays.time-utils
  (:require [clj-time.format :as f])
  (:gen-class
   :name filldays.time-utils))

(def ISOFormatter (f/formatters :date-time-no-ms))

(defn String->Date
  [str]
  (f/parse ISOFormatter str)
  )

(defn Date->String
  [date]
  (f/unparse ISOFormatter date)
  )

(defn Strings->Dates
  "Converts a list of date-strings to dates"
  [s]
  (map String->Date s)
  )

(defn Dates->Strings
  "Conevrts a list of dates to string"
  [s]
  (map Date->String s)
  )

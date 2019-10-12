(ns app.calendar
  (:require [clojure.spec.alpha :as s]))

(def weekdays
  #{:monday :tuesday :wednesday :thursday :friday :saturday :sunday})

(def months
  #{:january :february :march :april
    :may :june :july :august
    :september :october :november :december})

(s/def ::weekday weekdays)
(s/def ::monthday (s/int-in 0 32))
(s/def ::month months)
(s/def ::year int?)

(s/valid? ::weekday :monday)
(s/valid? ::monthday 31)
(s/valid? ::year 3455)

(def day {:day/weekday  ::weekday
          :day/monthday ::monthday})

(def week {:week/yearweek  int?
           :week/monthweek int?})

(def month {:month/name ::month
            :month/days [:day]})

(def year {:year/number :int})

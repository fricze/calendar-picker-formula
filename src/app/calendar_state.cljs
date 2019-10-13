(ns app.calendar-state
  (:require [app.get-month :refer
             [get-days-in-month
              dec-month inc-month]]))

(defn next-month [{:keys [calendar/year calendar/month] :as date}]
  (let [new-date (inc-month {:year year :month month})
        month    (get-days-in-month new-date)]
    {:calendar/year       (:year new-date)
     :calendar/month      (:month new-date)
     :calendar/month-days month}))

(defn prev-month [{:keys [calendar/year calendar/month] :as date}]
  (let [new-date (dec-month {:year year :month month})
        month    (get-days-in-month new-date)]
    {:calendar/year       (:year new-date)
     :calendar/month      (:month new-date)
     :calendar/month-days month}))

(defn initial-state []
  (let [today (js/Date.)
        month (.getMonth today)
        year  (.getYear today)]
    {:calendar/year       year
     :calendar/month      month
     :calendar/month-days (get-days-in-month {:year year :month month})}))

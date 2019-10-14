(ns app.calendar-view
  (:require [app.get-month :refer [get-days-in-month]]))

(def month-day
  {:component/name :component.name/month-day
   :component/type :component.type/month-day

   :component/props-path #(select-keys % [:day/date])})

(def month
  {:component/name :component.name/month
   :component/type :component.type/month

   :component/props-path #(select-keys % [:calendar/month-days])
   :component/sub-components
   {:month/day (fn [day]
                 (-> month-day
                     (update
                      :component/props merge {:day/date day})
                     (assoc
                      :key (.getTime day))))}

   :key :month})

(def prev-month
  {:component/name       :component.name/prev-month
   :component/type       :component.type/button
   :component/props-path #(select-keys % [:button/name])
   :component/props      {:button/name "prev"}
   :component/action     :calendar.action/prev-month

   :key :prev-month})

(def next-month
  {:component/name       :component.name/next-month
   :component/type       :component.type/button
   :component/props-path #(select-keys % [:button/name])
   :component/props      {:button/name "next"}
   :component/action     :calendar.action/next-month

   :key :next-month})

(def calendar-controls
  {:component/name :component.name/calendar-controls
   :component/type :component.type/box
   :key            :calendar-controls

   :component/children [prev-month next-month]})

(def month-name
  {:component/name :component.name/month-name
   :component/type :component.type/month-name

   :component/props-path #(select-keys % [:calendar/month-name])})

(def calendar
  {:component/name :component.name/calendar
   :component/type :component.type/calendar

   :component/props-path #(select-keys % [:calendar/month-days
                                          :calendar/month-name])
   :component/children   [calendar-controls month-name month]})

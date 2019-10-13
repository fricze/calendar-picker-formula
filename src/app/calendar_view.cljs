(ns app.calendar-view
  (:require [app.get-month :refer [get-days-in-month]]))

(def month-day
  {:component/name :component.name/month-day
   :component/type :component.type/month-day

   :component/props-path #(select-keys % [:day/date])})

(def month
  {:component/name :component.name/month
   :component/type :component.type/month

   :component/props-path #(select-keys % [:calendar/active-month-days])
   :component/sub-components
   {:month/day (fn [day]
                 (update month-day :component/props merge {:day/date day}))}})

(def prev-month
  {:component/name       :component.name/prev-month
   :component/type       :component.type/button
   :component/props-path #(select-keys % [:button/name])
   :component/props      {:button/name "prev"}
   :component/action     :calendar.action/prev-month})

(def next-month
  {:component/name       :component.name/next-month
   :component/type       :component.type/button
   :component/props-path #(select-keys % [:button/name])
   :component/props      {:button/name "next"}
   :component/action     :calendar.action/next-month})

(def calendar
  {:component/name :component.name/calendar
   :component/type :component.type/calendar

   :component/props-path #(select-keys % [:calendar/active-month-days
                                          :calendar/active-month
                                          :calendar/active-year])
   :component/children   [next-month prev-month month]})

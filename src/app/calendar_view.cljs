(ns app.calendar-view
  (:require [app.get-month :refer [get-days-in-month]]))

(defn calendar-view [{:keys [month year] :as date}]
  (get-days-in-month date))

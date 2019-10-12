(ns app.calendar-view
  (:require [app.get-month :refer [get-days-in-month]]))

(defn calendar-view [{:keys [month year #_days-per-row]}]
  (get-days-in-month month year))

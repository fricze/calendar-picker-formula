(ns app.main
  (:require [app.lib :as lib]
            [app.time :as time]
            [app.most-counter :refer [make-counter-3!]]
            [devtools.core :as devtools]
            [app.worker :refer [run]]
            [stylefy.core :as stylefy :refer [use-style]]
            [dirac.runtime]
            ["react-dom" :refer [render]]
            ["react-hyperscript" :as h]
            ["./calendar.js" :refer [getDaysInMonthRecursive Calendar]]
            [app.calendar-view :refer [calendar-view]]
            [app.get-month :refer [get-days-in-month-loop-3]]
            [app.dashboard.views :as dashboard :refer [files]]))

(devtools/install!)

(defmulti render-component :component/type)

(defmethod render-component :component.type/text
  []
  [:input {:type :text}])

(defmethod render-component :component.type/list
  []
  [:div])

(defmethod render-component :component.type/table
  []
  [:table])

(defmethod render-component :component.type/image
  []
  [:img {:src ""}])

(defmethod render-component :component.type/title
  []
  [:img {:src ""}])

(defmethod render-component :component.type/box
  []
  [:img {:src ""}])

(defmethod render-component :component.type/form
  []
  [:form])

(comment

 (defn get-calendar-block [props]
   (clj->js (calendar-view
             (-> props
                 js->clj
                 clojure.walk/keywordize-keys
                 (clojure.set/rename-keys
                  {:daysPerRow :days-per-row})))))

 (def root (h Calendar #js {:_getCalendarBlock get-calendar-block}))

 (render root (js/document.getElementById "root"))

 (make-counter-3!)
 )

(defn main! []
  #_(time/run)

  ;; maybe move somwhere like 'run'
  #_(stylefy/init)

  (println "[main]: loading"))

(defn reload! []

  #_(time/run)
  #_(println "[main] reloaded lib:" lib/c lib/d)
  #_(println "[main] reloaded:" a b))

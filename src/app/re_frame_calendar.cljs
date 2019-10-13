(ns app.re-frame-calendar
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [app.month :as month]
            [app.calendar-view :as cv]
            [app.calendar-state :as cs]
            [app.apple-calendar-style :as style]
            [clojure.string :as str]))

(defn get-props [{:keys [:component/props-path :component/props] :as c}]
  (props-path props))

(defmulti render-component :component/type)

(defmethod render-component :component.type/calendar
  [{:keys [:component/type :component/props-path
           :component/children :component/props]}]

  (let [component-props (props-path props)]
    [:div {:style {}}
     (->> children
          (map #(update % :component/props merge component-props))
          (map (fn [c]
               [render-component c])))]))

(defmethod render-component :component.type/button
  [{:keys [:component/props-path :component/props]}]

  (let [component-props (props-path props)]
    [:div (str (:button/name component-props))]))

(defmethod render-component :component.type/month
  [{:keys [:component/props-path :component/props
           :component/children :component/sub-components]}]

  (let [{:keys [:calendar/month-days]} (props-path props)
        month-day-component            (:month/day sub-components)]

    [:div {:style style/month}
     (->> month-days
          (map #(month-day-component %))
          (map (fn [day]
               [render-component day])))]))

(defn month-day-view [props]
  (let [{:keys [:day/date]} props]
    [:div {:style style/month-day}
     (.getDate date)]))

(defmethod render-component :component.type/month-day
  [{:keys [:component/props-path :component/props
           :component/children :component/sub-components]}]

  [month-day-view (props-path props)])

(defmethod render-component :default
  [{:keys [:component/name] :as component}]

  (js/console.warn [:component-type/not-implemented (:component/type component)])

  [:div
   {:key name}
   (js/JSON.stringify (clj->js component))])

;; -- Domino 2 - Event Handlers -----------------------------------------------


(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:calendar (cs/initial-state)}))

(rf/reg-event-db
 :time-color-change
 (fn [db [_ new-color-value]]
   (assoc db :time-color new-color-value)))


;; -- Domino 4 - Query  -------------------------------------------------------


(rf/reg-sub
 :month-days
 (fn [db _]
   (get-in db [:calendar :calendar/month-days])))


;; -- Domino 5 - View Functions ----------------------------------------------


;; #(rf/dispatch [:time-color-change (-> % .-target .-value)])

(defn ui
  []
  (let [month-days @(rf/subscribe [:month-days])]
    (render-component
     (update cv/calendar :component/props
             merge {:calendar/month-days month-days}))))

;; -- Entry Point -------------------------------------------------------------

(defn render
  []
  (reagent/render [ui]
                  (js/document.getElementById "root")))

(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (render))

(defn run-app!
  []
  (rf/dispatch-sync [:initialize]) ;; put a value into application state
  (render))

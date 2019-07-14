(ns app.dashboard.render
  (:require
   [app.icons.icon :refer [icon]]))

(defmulti render-component :component/type)

(defmethod render-component :component.type/text
  [{:keys [:component/content]}]
  [:span (str content)])

(defmethod render-component :component.type/date
  [{:keys [:component/content]}]
  [:span (str (.toLocaleDateString
               content
               "en-US"
               #js {:month "long" :day "numeric"}))])

(defmethod render-component :component.type/list
  []
  [:div])

(defmethod render-component :default
  [component]
  [:div (js/JSON.stringify (clj->js component))])

(defn table-rows [fields data]
  (map
   (fn [row] [:tr
              (map (fn [field]
                     [:td
                      (render-component
                       (merge row field
                              {:component/content
                               (row (:table.field/key field))}))])
                   fields)])
   data))

(defmethod render-component :component.type/table
  [{:keys [:component.table/headers :component.table/data
           :component.table/fields]}]

  [:table
   [:thead
    [:tr
     (map (fn [head] [:th (str head)]) headers)]]

   [:tbody
    (table-rows fields data)]])

(defmethod render-component :component.type/icon
  [{:keys [:file/extension]}]
  [:span [(icon extension)]])

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

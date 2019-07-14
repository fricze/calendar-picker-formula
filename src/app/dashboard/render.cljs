(ns app.dashboard.render
  (:require
   [app.icons.icon :refer [icon]]))

(defmulti render-component :component/type)

(defmethod render-component :component.type/text
  [{:keys [:component/content :component/name]}]
  (if content
    [:span (str content)]
    [:span (str name)]))

(defmethod render-component :component.type/date
  [{:keys [:component/content]}]
  [:span (str (.toLocaleDateString
               content
               "en-US"
               #js {:month "long" :day "numeric"}))])

(defmethod render-component :component.type/list
  [{:keys [:component.list/fields :component/data]}]
  [:div
   {:style {:outline "1px solid #333"}}
   (map (fn [field]
          [:div
           (render-component (merge field data
                                    {:component/content
                                     (data (:component.content/path data field))}))])
        fields)])

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
                               (row (:component.content/path field))}))])
                   fields)])
   data))

(defn header-cell
  [head]
  [:th (if (#{:component.header/empty} head)
         ""
         (str head))])

(defmethod render-component :component.type/table
  [{:keys [:component.table/headers :component/data
           :component.table/fields]}]

  [:table
   [:thead
    [:tr
     (map header-cell headers)]]

   [:tbody
    (table-rows fields data)]])

(defmethod render-component :component.type/icon
  [{:keys [:component/content]}]
  [:span [(icon content)]])

(defmethod render-component :component.type/image
  []
  [:img {:src ""}])

(defmethod render-component :component.type/title
  []
  [:img {:src ""}])

(defmethod render-component :component.type/box
  []
  [:img {:src ""}])

(defmethod render-component :component.type/text-button
  [{:keys [:component/name]}]
  [:button (str name)])

(defmethod render-component :component.type/icon-button
  [{:keys [:component/name]}]
  [:button (str name)])

(defmethod render-component :component.type/form
  []
  [:form])

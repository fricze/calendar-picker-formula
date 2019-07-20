(ns app.dashboard.render
  (:require
   ["./components.js" :refer [dateView dateTimeView]]
   [hx.react :refer [$]]
   [app.icons.icon :refer [icon]]))

(defmulti render-component :component/type)

(defmethod render-component :component.type/user-name
  [{:keys [:component/content :component/name]}]
  (if content
    [:span (str content)]
    [:span (str [:content/no-data name])]))

(defmethod render-component :component.type/status
  [{:keys [:component/content :component/name]}]
  (if content
    [:span (str content)]
    [:span (str [:content/no-data name])]))

(defmethod render-component :component.type/text
  [{:keys [:component/content :component/name]}]
  (if content
    [:span (str content)]
    [:span (str [:content/no-data name])]))

(defmethod render-component :component.type/date
  [{:keys [:component/content]}]
  [dateView {:date (js/Date.)}])

(defmethod render-component :component.type/date-time
  [{:keys [:component/content]}]
  [dateTimeView {:date (js/Date.)}])

(defn list-field [data]
  (fn [field]
    (let [path (or (:component.content/path field) identity)]
      [:div
       (render-component
        (assoc field :component/content
               (path data)))])))

(defmethod render-component :component.type/list
  [{:keys [:component.list/fields :component/data]}]
  [:div
   {:style {:outline "1px solid #333"}}
   (map (list-field data) fields)])

(defmethod render-component :default
  [component]
  (js/console.warn [:component-type/not-implemented (:component/type component)])
  [:div (js/JSON.stringify (clj->js component))])

(defn table-cell [data]
  (fn [field]
    (let [path (or (:component.content/path field) identity)]
      [:td
       (render-component
        (merge data field
               {:component/content
                (path data)}))])))

(defn table-rows [fields table-data]
  (map (fn [row] [:tr (map (table-cell row) fields)]) table-data))

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

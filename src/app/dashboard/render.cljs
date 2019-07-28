(ns app.dashboard.render
  (:require
   ["./components.js" :refer [dateView dateTimeView
                              text listElement listContent
                              titleStyle]]
   ["../calendar.js" :refer [Calendar]]
   ["./table.js" :refer [tableStyle tableHeader
                         tableHeaderCellStyle tableCellStyle
                         dayFromCalendar calendarStyle
                         tableRowStyle clientStatus]]
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
    [clientStatus {:status (cljs.core/name content)}]

    [:span (str [:content/no-data name])]))

(defmethod render-component :component.type/text
  [{:keys [:component/content :component/name] :as component}]
  (if content
    [text (str content)]
    [:span (str [:content/no-data name])]))

(defmethod render-component :component.type/date
  [{:keys [:component/content]}]
  [dateView {:date (js/Date.)}])

(defmethod render-component :component.type/date-time
  [{:keys [:component/content]}]
  [dateTimeView {:date (js/Date.)}])

(defn list-field [fields props-path]
  (fn [data]
    [listElement
     (props-path data)
     (map
      (fn [field]
        (let [datum (assoc field :component/content
                           ((:component.content/path field) data))]
          (render-component datum)))
      fields)]))

(defmethod render-component :component.type/list
  [{:keys [:component.list/fields :component/data
           :component/props-path :component/name]}]

  [listContent {:name (str name)} (map (list-field fields props-path) data)])

(defmethod render-component :default
  [component]
  (js/console.warn [:component-type/not-implemented (:component/type component)])
  [:div (js/JSON.stringify (clj->js component))])

(defn table-cell [data]
  (fn [field]
    (let [path (or (:component.content/path field) identity)]
      [tableCellStyle
       (render-component
        (merge data field
               {:component/content
                (path data)}))])))

(defn table-rows [fields table-data]
  (map (fn [row]
         [tableRowStyle (map (table-cell row) fields)]) table-data))

(defn header-cell
  [head]
  [tableHeaderCellStyle (if (#{:component.header/empty} head)
                          ""
                          (str head))])

(defmethod render-component :component.type/table
  [{:keys [:component.table/headers :component/data
           :component.table/fields]}]

  [tableStyle
   [tableHeader
    (map header-cell headers)]

   [:tbody
    (table-rows fields data)]])

(defmethod render-component :component.type/icon
  [{:keys [:component/content]}]
  [:span [(icon content)]])

(defmethod render-component :component.type/image
  []
  [:img {:src ""}])

(defmethod render-component :component.type/title
  [{:keys [:component/content]}]
  [titleStyle content])

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

(defmethod render-component :component.type/calendar
  [{:keys [:component/content]}]

  [Calendar {:DayView dayFromCalendar}]
  ;; lovely <3

  #_[calendarStyle
     (map
      (fn []
        [dayFromCalendar
         {:day 5 :events (clj->js [{:name "Clover Developer Meetup"}])}])
      (range 10))])

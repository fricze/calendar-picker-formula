(ns app.dashboard.views)

(def login
  {:components
   [{:component/name :component.name/login-form
     :component/type :component.type/form
     :component/content
     {:form/fields
      [{:field/name :workplace
        :field/type :field.type/text}
       {:field/name :username
        :field/type :field.type/text}
       {:field/name :password
        :field/type :field.type/password}]}}]})

(def welcome-screen
  {:components
   [{:component/name :component.name/hello-user
     :component/type :component.type/box}
    {:component/type :component.type/box
     :component/name :component.name/meetings}
    {:component/type :component.type/box
     :component/name :component.name/clients}
    {:component/type :component.type/box
     :component/name :component.name/files}
    {:component/type :component.type/box
     :component/name :component.name/promotion}]})

(def notifications
  {:components
   [{:component/name :component.name/notifications-title
     :component/type :component.type/title}
    {:component/type       :component.type/list
     :component/name       :component.name/notifications-list
     :component/data
     (map
      (fn [i]
        {:notification/title  "The Quick Brown Fox Jumps"
         :notification/status (if (even? i) "UNREAD" "READ")})
      (range 10))
     :component/props-path #(select-keys % [:notification/status])
     :component.list/fields
     [{:component/type         :component.type/text
       :component.content/path :notification/title
       :component/name         :component.name/notification-title}]
     ;; TODO: think about proper type for that field.
     ;; something that have: name, description, status
     :list/content         [{:component/type :component.type/notification
                             :component/name :component.name/notification}]}]})

(def dashboard
  {:components
   [{:component/name :component.name/dashboard-charts
     :component/type :component.type/movable-grid}]})

(def sales
  {:components
   [{:component/name :component.name/sales-charts
     :component/type :component.type/movable-grid}]})

(defn full-name [client]
  (->> [:client/first-name :client/last-name]
       (select-keys client)
       vals
       (clojure.string/join " ")))

(def clients
  {:components
   [{:component/name :component.name/clients-list
     :component/type :component.type/table

     :component.table/headers
     ["Clients" "Phone" "E-mail" "Position" "Industry"
      "Address" "State" "Country" "Status"]

     :component/data
     (map
      (fn []
        {:client/first-name "John"
         :client/last-name  "Smith"
         :client/e-mail     "johhn@smith.com"
         :client/phone      "570570570"
         :client/position   "Janitor!"
         :client/industry   "IT"
         :client/address    "Washington St. Hello 45/67"
         :client/state      "Washington"
         :client/country    "United States"
         :client/status     "no idea"})
      (range 10))

     ;; `{ first_name, last_name, phone, email, position,
     ;; industry, address, state, country, status }`

     :component.table/fields
     [{:component/type         :component.type/user-name
       :component.content/path full-name
       :component/name         :component.name/user-name}
      {:component/type         :component.type/text
       :component.content/path :client/phone
       :component/name         :component.name/phone}
      {:component/type         :component.type/text
       :component.content/path :client/e-mail
       :component/name         :component.name/e-mail}
      {:component/type         :component.type/text
       :component.content/path :client/position
       :component/name         :component.name/position}
      {:component/type         :component.type/text
       :component.content/path :client/industry
       :component/name         :component.name/industry}
      {:component/type         :component.type/text
       :component.content/path :client/address
       :component/name         :component.name/address}
      {:component/type         :component.type/text
       :component.content/path :client/state
       :component/name         :component.name/state}
      {:component/type         :component.type/text
       :component.content/path :client/country
       :component/name         :component.name/country}
      {:component/type         :component.type/status
       :component.content/path :client/status
       :component/name         :component.name/status}]

     :component.table/filters
     {:component/type    :component.type/modal
      :component/content [{:field/type :field.type/text
                           :field/name :field.name/client-search}
                          {:field/type :field.type/slider
                           :field/name :field.name/states-number}
                          {:button/type :button.type/save
                           :button/name :button.name/save}
                          {:button/type :button.type/cancel
                           :button/name :button.name/cancel}]}}]})

(def files
  {:components
   [{:component/name          :component.name/files-list
     :component/type          :component.type/table
     :component.table/headers [:component.header/empty "Name" "Modified" "Size"]
     :component/data
     (map
      (fn [icon]
        {:file/size          "250 KB"
         :file/modified-date (js/Date.)
         :file/name          "Metro Berlin Map"
         :file/link-to       "Metro Berlin Map.pdf"
         :file/extension     icon})
      [:pdf :ms-word :ms-publisher :text :zip])
     :component.table/fields
     [{:component/type         :component.type/icon
       :component.content/path :file/extension
       :component/name         :component.name/file-icon}
      {:component/type         :component.type/text
       :component.content/path :file/name
       :component/name         :component.name/file-name}
      {:component/type         :component.type/date
       :component.content/path :file/modified-date
       :component/name         :component.name/modified-date}
      {:component/type         :component.type/text
       :component.content/path :file/size
       :component/name         :component.name/file-size}]}

    {:component/name :component.name/file-details
     :component/type :component.type/list
     :component/data {:file/size          "250 KB"
                      :file/modified-date (js/Date.)
                      :file/name          "Metro Berlin Map"
                      :file/link-to       "Metro Berlin Map.pdf"
                      :file/extension     :pdf}
     :component.list/fields
     [{:component/type         :component.type/icon
       :component.content/path :file/extension
       :component/name         :component.name/file-icon}
      {:component/type         :component.type/text
       :component.content/path :file/size
       :component/name         :component.name/file-name}
      {:component/type         :component.type/text
       :component.content/path :file/link-to
       :component/name         :component.name/file-name}
      {:component/type :component.type/text-button
       :component/name :component.name/download}]}

    {:component/name :component.name/file-details
     :component/type :component.type/list
     :component/data {:file/size          "250 KB"
                      :file/modified-date (js/Date.)
                      :file/name          "Metro Berlin Map"
                      :file/link-to       "Metro Berlin Map.pdf"
                      :file/extension     :pdf}
     :component.list/fields
     [{:component/type         :component.type/icon
       :component.content/path :file/extension
       :component/name         :component.name/file-icon}
      {:component/type         :component.type/text
       :component.content/path :file/name
       :component/name         :component.name/file-name}
      {:component/type         :component.type/text
       :component.content/path :file/extension
       :component/name         :component.name/file-extension}
      {:component/type         :component.type/date-time
       :component.content/path :file/modified-date
       :component/name         :component.name/modified-date-time}
      {:component/type :component.type/text-button
       :component/name :component.name/share}
      {:component/type :component.type/text-button
       :component/name :component.name/fill-sign}
      {:component/type :component.type/text-button
       :component/name :component.name/rename}
      {:component/type :component.type/text-button
       :component/name :component.name/move}
      {:component/type :component.type/text-button
       :component/name :component.name/download}
      {:component/type :component.type/text-button
       :component/name :component.name/delete}
      {:component/type :component.type/icon-button
       :component/name :component.name/add}]}]})

(def dashboard-app {})

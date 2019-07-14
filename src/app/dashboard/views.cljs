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
    {:component/type :component.type/list
     :component/name :component.name/notifications-list
     ;; TODO: think about proper type for that field.
     ;; something that have: name, description, status
     :list/content   [{:component/type :component.type/notification
                       :component/name :component.name/notification}]}]})

(def dashboard
  {:components
   [{:component/name :component.name/dashboard-charts
     :component/type :component.type/movable-grid}]})

(def sales
  {:components
   [{:component/name :component.name/sales-charts
     :component/type :component.type/movable-grid}]})

(def clients
  {:components
   [{:component/name :component.name/clients-list
     :component/type :component.type/table
     :component.table/headers
     ["Clients" "Phone" "E-mail" "Position" "Industry"
      "Address" "State" "Country" "Status"]
     :component.table/fields
     [{:component/type :component.type/user-name
       :component/name :component.name/user-name}
      {:component/type :component.type/text
       :component/name :component.name/phone}
      {:component/type :component.type/text
       :component/name :component.name/e-mail}
      {:component/type :component.type/text
       :component/name :component.name/position}
      {:component/type :component.type/text
       :component/name :component.name/industry}
      {:component/type :component.type/address
       :component/name :component.name/address}
      {:component/type :component.type/text
       :component/name :component.name/state}
      {:component/type :component.type/text
       :component/name :component.name/country}
      {:component/type :component.type/status
       :component/name :component.name/status}]
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
     :component.table/fields
     [{:component/type :component.type/image
       :component/name :component.name/file-icon}
      {:component/type :component.type/text
       :component/name :component.name/file-name}
      {:component/type :component.type/text
       :component/name :component.name/modified-date}
      {:component/type :component.type/text
       :component/name :component.name/file-size}]}
    {:component/name :component.name/files-list
     :component/type :component.type/list
     :list/content   [{:component/type :component.type/image
                       :component/name :component.name/file-icon}
                      {:component/type :component.type/text
                       :component/name :component.name/file-name}
                      {:component/type :component.type/text
                       :component/name :component.name/file-extension}
                      {:component/type :component.type/text
                       :component/name :component.name/modified-date-time}
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

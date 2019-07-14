(ns app.dashboard.views)

(def login
  {:components
   [{:component/name :component.name/login-form
     :component/type :component.type/form
     :component/content
     {:fields [{:field/name :workplace
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
     :component/name :component.name/notifications-list}]})

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
     :table/headers
     ["Clients" "Phone" "E-mail" "Position" "Industry"
      "Address" "State" "Country" "Status"]
     :table/fields
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
      {:component/type :component.type/text
       :component/name :component.name/address}
      {:component/type :component.type/text
       :component/name :component.name/state}
      {:component/type :component.type/text
       :component/name :component.name/country}
      {:component/type :component.type/status
       :component/name :component.name/status}
      ]}]})

(def dashboard-app {})

(ns app.time
  (:require [hx.react :as hx :refer [defnc]]
            [cljs-styled-components.core :refer
             [defstyled defkeyframes theme-provider
              clj-props set-default-theme!]]
            [cljs-css-modules.macro :refer-macros [defstyle]]
            [hx.hooks :as hooks]
            [stylefy.core :as stylefy :refer [use-style]]
            ["react-dom" :as react-dom]))

(def colors {:color/green "rgb(62, 76, 39)"})

(defstyle form
  [:.task-button {:font-family   "iA Writer Duo S"
                  :border        "none"
                  :border-bottom "1px solid #333"
                  :padding       "0.5rem 1rem"
                  :font-size     14}]

  [:.task-button:focus {:outline    "1px solid #333"
                        :box-shadow "1px 1px 1px #333"}
   [:&:after {:content "' +'"}]]

  [:.label-style {:color        (:color/green colors)
                  :font-weight 700
                  :font-size   16}])

(defnc Textarea [{:keys [value update]}]
  [:label
   [:div
    [:span {:class-name (:label-style form)} "Content"]]

   [:textarea {:on-change update
               :value     value
               :style     {:font-family   "iA Writer Duo S"
                           :border        "none"
                           :border-bottom "1px solid #333"
                           :resize        "none"
                           :padding       "0.5rem"
                           :width         "100%"
                           :height        "6rem"
                           :min-height    "3rem"
                           :outline       "none"
                           :font-size     14}}]])

(defnc Text [{:keys [value update]}]
  [:label
   [:div
    [:span {:class-name (:label-style form)} "Title"]]

   [:input {:on-change update
            :style     {:font-family   "iA Writer Duo S"
                        :border        "none"
                        :border-bottom "1px solid #333"
                        :padding       "0.5rem"
                        :resize        "vertical"
                        :width         "100%"
                        :outline       "none"
                        :font-size     14}
            :value     value}]])

(defnc Button [{:keys [text on-click]}]
  [:button {:on-click   on-click
            :class-name (:task-button form)}
   text])

(defn space []
  [:div {:style {}}])

(defn event->value [e]
  (-> e .-target .-value))

(defnc Task []
  (let [[task dispatch] (hooks/useReducer
                         (fn [state action]
                           (case (:action/name action)
                             :set/title       (assoc state :task/title
                                                     (:action/value action))
                             :set/description (assoc state :task/description
                                                     (:action/value action))
                             state))

                         {:task/title       ""
                          :task/description ""})]

    [:div
     {:style {:display        "flex"
              :flex-direction "column"
              :width          "20vw"}}
     [Text {:update #(dispatch {:action/name  :set/title
                                :action/value (event->value %)})
            :value  (:task/title task)}]

     [Textarea {:update #(dispatch {:action/name  :set/description
                                    :action/value (event->value %)})
                :value  (:task/description task)}]

     [Button {:text "save" :on-click #(dispatch {:action/name :task/save})}]]))

(defnc MyComponent [{:keys [initial-name]}]
  ;; use React Hooks for state management
  (let [[name update-name] (hooks/useState initial-name)
        [state dispatch]   (hooks/useReducer
                            (fn [state action]
                              (case action
                                :new/girl (conj state :new/gurl)
                                state))
                            [:lovely/girls])]

    [:<>
     [:div
      {:style {:font-family "iA Writer Duo S"
               :font-size   14}}
      [Task]]]))

(defn run []
  (react-dom/render
   ;; hx/f transforms Hiccup into a React element.
   ;; We only have to use it when we want to use hiccup outside of
   ;; `defnc` / `defcomponent`
   (hx/f [MyComponent {:initial-name "React in CLJS"}])

   (. js/document getElementById "root")))

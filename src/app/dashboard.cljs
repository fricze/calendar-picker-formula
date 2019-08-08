(ns app.dashboard
  (:require [hx.react :as hx :refer [defnc]]
            [cljs-css-modules.macro :refer-macros [defstyle]]
            [hx.hooks :as hooks]
            [app.dashboard.render :refer [render-component]]
            [app.dashboard.views :as dashboard]
            ["react-useinterval" :as use-interval]
            ["react-pose" :refer [] :default posed]
            ["react-dom" :as react-dom]))

(def use-state hooks/useState)

#_(defnc Root []
    [:div
     #_{:style {:marginLeft "35vw"}}

   ;; [:h1
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (str "(:components dashboard/notifications)")]

   ;; [:div
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (map render-component (:components dashboard/notifications))]


   ;; [:h1
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (str "(:components dashboard/todo)")]

   ;; [:div
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (map render-component (:components dashboard/todo))]


   ;; [:h1
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (str "(:components dashboard/clients)")]

   ;; [:div
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (map render-component (:components dashboard/clients))]


   ;; [:h1
   ;;  {:style {:font-family "Open Sans SemiBold"
   ;;           :font-size   14}}
   ;;  (str "(:components dashboard/calendar)")]

   ;; [:div
   ;;  {:style {:font-family "Open Sans SemiBold"
   ;;           :font-size   14}}
   ;;  (map render-component (:components dashboard/calendar))]

   ;; [:div
   ;;  {:style {:height 100}}]

   ;; [:h1
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (str "(:components dashboard/files)")]

   ;; [:div
   ;;  {:style {:font-family "iA Writer Duo S"
   ;;           :font-size   14}}
   ;;  (map render-component (:components dashboard/files))]
     ])

(def window-height (aget js/window "innerHeight"))
(def window-width  (aget js/window "innerWidth"))

(defnc Root []
  [:div
   {:style {:display               "grid"
            :height                "100vh"
            :width                 "100vw"
            :grid-template-columns "repeat(30, 1fr)"
            :grid-template-rows    "repeat(30, 1fr)"}}

   #_[:div {:draggable true :ondragstart "event.dataTransfer.setData('text/plain', 'This text may be dragged')"
            :onDrag    (fn [] (js/console.log :draggy))}
      [:strong "drag me"]]

   (let [box           (.div posed #js {:draggable true
                                        :dragging  #js {:scale 1.2}
                                        :dragEnd   #js {:scale 1}})
         [row set-row] (use-state 1)
         [col set-col] (use-state 1)]
     [box
      {:onDragEnd (fn [e]
                    (let [row-height (/ window-height 30)
                          col-height (/ window-width 30)

                          row (- 30 (int (/ (- window-height (aget e "y")) row-height)))
                          col (- 30 (int (/ (- window-width (aget e "x")) col-height)))]
                      (set-row row)
                      (set-col col)))

       :style {:grid-row    row
               :grid-column col
               :border      "1px solid #333" :cursor "pointer"}}
      ""])])

(defn run-app! []
  (react-dom/render
   ;; hx/f transforms Hiccup into a React element.
   ;; We only have to use it when we want to use hiccup outside of
   ;; `defnc` / `defcomponent`
   (hx/f [Root])

   (. js/document getElementById "root")))

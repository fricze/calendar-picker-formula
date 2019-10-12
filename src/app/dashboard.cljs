(ns app.dashboard
  (:require [hx.react :as hx :refer [defnc]]
            [cljs-css-modules.macro :refer-macros [defstyle]]
            [hx.hooks :as hooks]
            [app.dashboard.render :refer [render-component]]
            [app.dashboard.views :as dashboard]
            ["react-useinterval" :as use-interval]
            ["react" :refer [memo]]
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

(defnc GridBox [{:keys [col-height window-width window-height row-height
                        set-row set-col row col set-diff set-show-col]}]
  (let [box (.div posed #js {:draggable true
                             :dragging  #js {:scale 1.2}})]
    [box
     {:onValueChange #js {:x (fn [x]
                               (let [x         (+ x (* (dec col) col-height))
                                     diff      (/ (- window-width x) col-height)
                                     new-col   (- 30 (int diff))
                                     real-diff (js/Math.abs (- (js/Math.floor diff) diff))]

                                 (set-diff real-diff)
                                 (set-show-col (inc new-col))

                                 #_(when (not= new-col show-col)
                                     (set-show-col (inc new-col)))))}
      :onDragEnd (fn [e]
                   (let [row (- 30 (int (/ (- window-height (aget e "y")) row-height)))
                         col (- 30 (int (/ (- window-width (aget e "x")) col-height)))]
                     (set-row row)
                     (set-col col)))

      :style {:grid-row    row
              :grid-column col
              :border      "1px solid #333"
              :cursor      "pointer"}}
     ""]))

(def grid-box (memo GridBox))

(defnc Root []
  [:div
   {}
   (let [row-height (/ window-height 30)
         col-height (/ window-width 30)

         [show-col set-show-col] (use-state 0)
         [diff set-diff]         (use-state 0)

         [row set-row] (use-state 1)
         [col set-col] (use-state 1)]

     [:div
      {:style {:display               "grid"
               :height                "100vh"
               :width                 "100vw"
               :grid-template-columns "repeat(30, 1fr)"
               :grid-template-rows    "repeat(30, 1fr)"}}

      [:div {:style {:opacity (if (and
                                   (< (+ diff 0.5) 1.2)
                                   (> (+ diff 0.5) 0.7)) 0.7 0) :grid-row "1/30" :grid-column show-col :width 2 :background "rgb(121, 182, 242)"}}]
      ;; [:div {:style {:opacity 0.7 :grid-row "1/30" :grid-column (dec show-col) :width 2 :background "rgb(121, 182, 242)"}}]

      [grid-box
       {:col           col
        :col-height    col-height
        :window-width  window-width
        :window-height window-height
        :row-height    row-height
        :set-row       set-row
        :set-col       set-col
        :row           row
        :set-diff      set-diff
        :set-show-col  set-show-col}]])])

(defn run-app! []
  (react-dom/render
   ;; hx/f transforms Hiccup into a React element.
   ;; We only have to use it when we want to use hiccup outside of
   ;; `defnc` / `defcomponent`
   (hx/f [Root])

   (. js/document getElementById "root")))

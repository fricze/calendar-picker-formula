(ns app.dashboard
  (:require [hx.react :as hx :refer [defnc]]
            [cljs-css-modules.macro :refer-macros [defstyle]]
            [hx.hooks :as hooks]
            [app.dashboard.render :refer [render-component]]
            [app.dashboard.views :as dashboard :refer [files]]
            ["react-useinterval" :as use-interval]
            ["react-pose" :refer [] :default posed]
            ["react-dom" :as react-dom]))

(defnc Root []
  [:<>
   [:div
    {:style {:font-family "iA Writer Duo S"
             :font-size   14}}
    (map render-component (:components files))]])

(defn run-app! []
  (react-dom/render
   ;; hx/f transforms Hiccup into a React element.
   ;; We only have to use it when we want to use hiccup outside of
   ;; `defnc` / `defcomponent`
   (hx/f [Root])

   (. js/document getElementById "root")))

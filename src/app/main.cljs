(ns app.main
  (:require [devtools.core :as devtools]
            [app.re-frame-calendar :as re-c]
            [stylefy.core :as stylefy :refer [use-style]]
            [dirac.runtime]))

(devtools/install!)

(defn main! []
  (re-c/run-app!)

  (println "[main]: loading"))

(defn reload! []
  (re-c/run-app!))

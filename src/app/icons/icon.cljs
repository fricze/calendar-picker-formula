(ns app.icons.icon
  (:require
   ["./MsPowerPoint.js" :default ms-power-point-icon]
   ["./MsPublisher.js" :default ms-publisher-icon]
   ["./MsWord.js" :default ms-word-icon]
   ["./Pdf.js" :default pdf-icon]
   ["./Text.js" :default text-icon]
   ["./Zip.js" :default zip-icon]))

(def icon
  {:zip          zip-icon
   :text         text-icon
   :pdf          pdf-icon
   :ms-word      ms-word-icon
   :ms-publisher ms-publisher-icon
   :power-point  ms-power-point-icon})

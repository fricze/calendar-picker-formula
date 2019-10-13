(ns app.apple-calendar-style)

(def month-day
  {:color       :#fff
   :text-align  :center
   :font-size   14
   :height      36
   :line-height 2
   :font-family "Helvetica Neue"
   :flex-basis  "14.2857143%"})

(def month
  {:background     :#000
   :display        :flex
   :flex-direction :row
   :margin         "0 auto"
   :box-shadow     "0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24)"
   :flex-wrap      :wrap
   :width          500})

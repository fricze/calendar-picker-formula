(ns app.apple-calendar-style)

(def month-day
  {:color       :#fff
   :text-align  :center
   :font-size   14
   :height      36
   :line-height 2.3
   :font-family "Helvetica Neue"
   :flex-basis  "14.2857143%"})

(def month
  {:background     :#000
   :display        :flex
   :flex-direction :row
   :margin         "0 auto"
   :box-shadow     "0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)"
   :flex-wrap      :wrap
   :width          500})

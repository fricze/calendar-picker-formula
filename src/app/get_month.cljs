(ns app.get-month
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(comment

  "soooo! you don't have to count from left to right!
You know which weekday starts and which weekday finishes week… so you can count
require number of days to right and left")

(defn dec-month [{:keys [month year]}]
  (if (= month 0)
    {:month 11 :year (dec year)}
    {:month (dec month) :year year}))

(defn inc-month [{:keys [month year]}]
  (if (= month 11)
    {:month 0 :year (inc year)}
    {:month (inc month) :year year}))

(s/def ::year int?)
(s/def ::month int?)

(s/def ::date
  (s/keys :req-un [::month ::year]))

(s/fdef dec-month
  :args (s/cat :date ::date)
  :ret ::date)

(s/fdef inc-month
  :args (s/cat :date ::date)
  :ret ::date)

(def month->days-no
  [31 28 31 30 31 30
   31 31 30 31 30 31])

(def days-no {:monday 1 :tuesday 2 :wednesday 3
              :thursday 4 :friday 5 :saturday 6 :sunday 0})

(def days-to-end-of-week
  {1 6
   2 5
   3 4
   4 3
   5 2
   6 1
   0 0})

(def days-to-start-of-week
  {1 0
   2 1
   3 2
   4 3
   5 4
   6 5
   0 6})

(defn get-days-from-next-month [date last-day]
  (let [days                 (days-to-end-of-week last-day)
        {:keys [month year]} (inc-month date)

        last-in-month  (inc days)
        first-in-month 1]

    (map #(js/Date. year month %)
         (range first-in-month last-in-month))))

(defn get-days-from-prev-month [date first-day]
  (let [days                 (days-to-start-of-week first-day)
        {:keys [month year]} (dec-month date)

        last-in-month  (inc (month->days-no month))
        first-in-month (- last-in-month days)]

    (map #(js/Date. year month %)
         (range first-in-month last-in-month))))

(defn get-days-from-month [{:keys [month year]}]
  (let [days (range 1 (inc (month->days-no month)))]
    (map #(js/Date. year month %) days)))

(defn get-days-in-month [{:keys [month year] :as date}]
  (let [current-month-days (get-days-from-month date)
        prev-month-days    (get-days-from-prev-month
                            date (-> current-month-days first .getDay))
        next-month-days    (get-days-from-next-month
                            date (-> current-month-days last .getDay))]

    (concat prev-month-days current-month-days next-month-days)))

;; monday === 1
;; sunday === 0

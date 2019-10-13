(ns app.get-month)

(comment

  "soooo! you don't have to count from left to right!
You know which weekday starts and which weekday finishes week… so you can count
require number of days to right and left")

(defn same-month [date month]
  (= (.getMonth date) month))

(defn --get-days-in-month [month year]
  (loop [day  1
         days []]
    (let [date (js/Date. year month day)]
      (if (= (.getMonth date) month)
        (recur (inc day) (conj days date))
        days))))

(defn dec-month [{:keys [month year]}]
  (if (= month 0)
    {:month 11 :year (dec year)}
    {:month (dec month) :year year}))

(defn inc-month [{:keys [month year]}]
  (if (= month 11)
    {:month 0 :year (inc year)}
    {:month (inc month) :year year}))

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
  (let [days-next            (days-to-end-of-week last-day)
        {:keys [month year]} (inc-month date)

        last-in-month  (inc days-next)
        first-in-month 1]

    (map #(js/Date. year month %)
         (range first-in-month last-in-month))))

(defn get-days-from-prev-month [date first-day]
  (let [days-prev            (days-to-start-of-week first-day)
        {:keys [month year]} (dec-month date)

        last-in-month  (inc (month->days-no month))
        first-in-month (- last-in-month days-prev)]

    (map #(js/Date. year month %)
         (range first-in-month last-in-month))))

(defn get-days-in-month [{:keys [month year] :as date}]
  (let [days               (range 1 (inc (month->days-no month)))
        current-month-days (map #(js/Date. year month %) days)

        prev-month-days (get-days-from-prev-month
                         date (-> current-month-days first .getDay))

        next-month-days (get-days-from-next-month
                         date (-> current-month-days last .getDay))]

    (concat prev-month-days current-month-days next-month-days)))

(defn get-days-in-calendar-month [{:keys [month year]}]
  (loop [day  1
         days []]
    (let [date (js/Date. year month day)]
      (if (= (.getMonth date) month)
        (recur (inc day) (conj days date))
        days))))

;; monday === 1
;; sunday === 0

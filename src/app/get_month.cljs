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

(defn get-prev-month [{:keys [month year]}]
  (if (= month 0)
    {:month 11 :year (dec year)}
    {:month (dec month) :year year}))

(defn get-next-month [{:keys [month year]}]
  (if (= month 11)
    {:month 0 :year (inc year)}
    {:month (inc month) :year year}))

(def days-in-month
  [31 28 31 30 31 30 31 31 30 31 30 31])

(def days-no {:monday 1 :tuesday 2 :wednesday 3
              :thursday 4 :friday 5 :saturday 6 :sunday 0})

(def days-to-end-of-month
  {1 6
   2 5
   3 4
   4 3
   5 2
   6 1
   0 0})

(def days-to-start-of-month
  {1 0
   2 1
   3 2
   4 3
   5 4
   6 5
   0 6})

#_(defn -get-days-in-month [month year]
    (let [days               (range 1 (inc (nth days-in-month month)))
          current-month-days (map (fn [day] (js/Date. year month day)) days)]
      current-month-days))

(defn get-days-in-next-month [month year last-day]
  (let [days-next            (days-to-end-of-month last-day)
        {:keys [month year]} (get-next-month {:month month :year year})]
    (map #(js/Date. year month %)
       (range 1 (inc days-next)))))

(defn get-days-in-prev-month [month year first-day]
  (let [days-prev            (days-to-start-of-month first-day)
        {:keys [month year]} (get-prev-month {:month month :year year})
        days-in-prev-month   (inc (nth days-in-month month))]
    (map #(js/Date. year month %)
       (range (- days-in-prev-month days-prev)
              days-in-prev-month))))

(defn get-days-in-month [{:keys [month year] :as date}]
  (let [days               (range 1 (inc (nth days-in-month month)))
        current-month-days (map (fn [day] (js/Date. year month day)) days)

        prev-month-days    (get-days-in-prev-month
                            month year
                            (-> current-month-days first .getDay))

        next-month-days    (get-days-in-next-month
                            month year
                            (-> current-month-days last .getDay))]

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

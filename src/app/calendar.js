import ReactDOM, { render, } from "react-dom"
import h from "react-hyperscript"
import React, { useState, } from "react"
import styled from "styled-components"

const s = styled.default

export function getDaysInMonthRecursive(
  month,
  year,
  date = new Date(year, month, 1),
  days = []
) {
  if (date.getMonth() === month) {
    const nextDate = new Date(date)
    nextDate.setDate(date.getDate() + 1)

    return getDaysInMonthRecursive(
      month,
      year,
      nextDate,
      days.concat([new Date(date),])
    )
  }

  return days
}

function getDaysInMonth(month, year) {
  const date = new Date(year, month, 1)
  const days = []

  while (date.getMonth() === month) {
    days.push(new Date(date))
    date.setDate(date.getDate() + 1)
  }

  return days
}

const weekDays = [
  "Sunday",
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
] // .map(d => d.slice(0, 3))

const weekdayStyled = s.div({
  background: "#0E57B8",
  width: "160px",
  flexShrink: 0,
  borderRight: "1px solid #D3D1D1",
  textTransform: "uppercase",
  height: 38,
  lineHeight: 2.6,
  paddingLeft: 34,
  color: "#fff",
})

const weekdaysStyled = s.div({
  display: "flex",
})

const weekDaysView = h(
  weekdaysStyled,
  { className: "weekdays", key: "weekdays", },
  weekDays.map(name =>
    h(weekdayStyled, { key: name, className: "weekday", }, name)
  )
)

const useValue = handler => e => handler(e.target.value)

const useCalendarState = () => {
  const [[month, year,], setMonthYear,] = useState([4, 2027,])

  const date = new Date(year, month)
  const monthName = date.toLocaleDateString("en-US", { month: "long", })
  const yearName = date.toLocaleDateString("en-US", { year: "numeric", })

  // System boundaries. Allow only numbers between 0 and 11
  const setMonth = value => {
    const monthNumber = Number(value)
    const newMonthValue =
      monthNumber > 11 ? 11 : monthNumber < 0 ? 0 : monthNumber

    return setMonthYear([newMonthValue, year,])
  }

  const setNextMonth = () => {
    switch (month) {
      case 11:
        setMonthYear([0, year + 1,])
        break
      default:
        setMonthYear([month + 1, year,])
    }
  }

  const setPrevMonth = () => {
    switch (month) {
      case 0:
        setMonthYear([11, year - 1,])
        break
      default:
        setMonthYear([month - 1, year,])
    }
  }

  // System boundaries. Set year number
  const setYear = value => {
    const yearNumber = Number(value)
    const newYearValue = yearNumber < 1990 ? 1990 : yearNumber

    return setMonthYear([month, newYearValue,])
  }

  return {
    setYear,

    setMonth,
    setNextMonth,
    setPrevMonth,

    month,
    year,
  }
}

const getNextDays = (month, year) =>
  month === 11
    ? getDaysInMonthRecursive(0, year + 1)
    : getDaysInMonthRecursive(month + 1, year)

const getPrevDays = (month, year) =>
  month === 0
    ? getDaysInMonthRecursive(11, year - 1)
    : getDaysInMonthRecursive(month - 1, year)

const getCalendarBlock = ({ month, year, daysPerRow, }) => {
  const currentMonthDaysCollection = getDaysInMonthRecursive(month, year).map(
    date => ({ date, current: true, })
  )

  // System boundaries. Make sure to go correctly to next month in following year
  const nextDaysCollection = getNextDays(month, year).map(date => ({
    date,
    nextMonth: true,
  }))
  // System boundaries. Make sure to go correctly to previus month in previous year
  const prevDaysCollection = getPrevDays(month, year).map(date => ({
    date,
    prevMonth: true,
  }))

  const firstDay = currentMonthDaysCollection[0].date
  const howFarToLeft = firstDay.getDay() % daysPerRow

  const lastFromPrevDays = prevDaysCollection.slice(
    Math.max(prevDaysCollection.length - howFarToLeft, 1)
  )

  const currentWithPrev = lastFromPrevDays.concat(currentMonthDaysCollection)

  const rows = Math.ceil(currentWithPrev.length / daysPerRow)
  const shouldHaveCells = rows * daysPerRow
  const howFarToRight = shouldHaveCells - currentWithPrev.length

  const firstFromNextDays = nextDaysCollection.slice(0, howFarToRight)

  return currentWithPrev.concat(firstFromNextDays)
}

const dayView = ({ daysPerRow, day, idx, }) => {
  const column = (idx % daysPerRow) + 1

  return h(
    "span",
    {
      // this key should be unique enough,
      // timestamp seems to be good identifier for Date
      key: day.getTime(),
      style: {
        gridColumn: column,
      },
      className: "day",
    },
    day.getDate()
  )
}

export const Calendar = ({
  daysPerRow = 7,
  _getCalendarBlock = getCalendarBlock,
  DayView = dayView,
}) => {
  const {
    setYear,
    setMonth,
    month,
    year,

    setPrevMonth,
    setNextMonth,

    setPrevYear,
    setNextYear,
  } = useCalendarState()

  const allDaysCollection = _getCalendarBlock({ month, year, daysPerRow, })

  const buttons = () =>
    h([
      h(
        "button",
        {
          key: "prevMonth",
          onClick: setPrevMonth,
        },
        "prev month"
      ),

      h(
        "button",
        {
          key: "nextMonth",
          onClick: setNextMonth,
        },
        "next month"
      ),
    ])

  return h(
    "div",
    { className: "box", },
    h("div", [
      h("input", {
        key: "month-input",
        type: "number",
        value: month,
        // System boundaries.
        onChange: useValue(setMonth),
      }),

      h("input", {
        key: "year-input",
        type: "number",
        value: year,
        // System boundaries.
        onChange: useValue(setYear),
      }),

      h(buttons),

      weekDaysView,

      h(
        "div",
        {
          className: "grid",
          key: "grid",
          style: {
            display: "grid",
            gridTemplateColumns: `repeat(${daysPerRow}, 160px)`,
          },
        },
        allDaysCollection.map((day, idx) => DayView({ daysPerRow, day, idx, }))
      ),
    ])
  )
}

// const root = h(Calendar)

// render(root, document.getElementById("root"))

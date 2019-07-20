import h from "react-hyperscript"
import styled from "styled-components"

console.log(styled)

export const dateView = ({ date, }) =>
  h(
    "span",
    date.toLocaleDateString("en-US", { month: "long", day: "numeric", })
  )

export const dateTimeView = ({ date, }) =>
  h(
    "span",
    date.toLocaleDateString("en-US", {
      month: "long",
      day: "numeric",
      hour: "numeric",
      minute: "numeric",
    })
  )

// const Row = styled.h1`
//   background: blue;
//   height: 70px;
// `

export const ClientRow = ({ date, }) =>
  h(
    "tr",
    date.toLocaleDateString("en-US", {
      month: "long",
      day: "numeric",
      hour: "numeric",
      minute: "numeric",
    })
  )

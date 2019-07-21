import h from "react-hyperscript"
import styled from "styled-components"

const s = styled.default

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

const Row = s.h1`
  background: blue;
  height: 70px;
`

export const ClientRow = ({ date, }) =>
  h(
    Row,
    date.toLocaleDateString("en-US", {
      month: "long",
      day: "numeric",
      hour: "numeric",
      minute: "numeric",
    })
  )

const listElementStatusStyle = s.div({
  height: "100%",
  display: "flex",
  alignItems: "center",
  justifyContent: "flex-start",
})

const listElementStyle = s.div({
  display: "flex",
  flexDirection: "row",
  background: "#fff",
  height: "40px",
  fontSize: "12px",
  fontFamily: "Open Sans Light",
  [`& ${listElementStatusStyle}`]: {
    width: "20px",
  },
})

const listStyle = s.div({
  outline: "1px solid #999",
  width: "362px",
  [`& ${listElementStyle}`]: {
    margin: "40px 0",
  },
})

const listTitleStyle = s.h1({
  fontSize: "18px",
  fontFamily: "Open Sans Light",
  fontWeight: "normal",
})

export const listContent = ({ children, }) =>
  h(listStyle, [h(listTitleStyle, {}, "Notifications"), children,])

const statusColor = status =>
  status === "UNREAD" ? "#495AFF" : status === "READ" ? "#21A5FF" : "#999"

const listElementStatus = ({ status, }) =>
  h(listElementStatusStyle, [
    h(
      s.div({
        width: "10px",
        height: "10px",
        borderRadius: "50%",
        background: statusColor(status),
      })
    ),
  ])

export const listElement = props => {
  const { children, } = props
  const status = props["notification/status"]

  return h(listElementStyle, [h(listElementStatus, { status, }), children,])
}

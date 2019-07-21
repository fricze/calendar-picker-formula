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

export const titleStyle = s.h1({
  fontSize: "18px",
  fontFamily: "Open Sans Light",
  margin: 0,
  fontWeight: "normal",
})

const textStyle = s.p({ margin: 0, })

export const text = ({ children, }) => h(textStyle, [children,])

const listElementStyle = s.div(({ photo, }) => ({
  display: "grid",
  background: "#fff",
  height: "40px",
  fontSize: "12px",
  fontFamily: "Open Sans Light",

  gridTemplateColumns: photo ? "20px 40px auto" : "30px auto",
  gridTemplateRows: "20px",
  gridTemplateAreas: photo
    ? `
    "status photo header"
    "status photo content"
  `
    : `"status header"
       "status content"
   `,
  [`& ${listElementStatusStyle}`]: {
    gridArea: "status",
  },

  [`& ${titleStyle}`]: {
    gridArea: "header",
    fontSize: "12px",
  },

  [`& ${textStyle}`]: {
    gridArea: "content",
  },
}))

const listStyle = s.div({
  width: "362px",
  display: "flex",
  flexDirection: "column",
  [`& ${listElementStyle}`]: {
    margin: "20px 0",
  },
})

export const listContent = ({ children, }) => h(listStyle, [children,])

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
  const { children, status, photo, } = props

  return h(listElementStyle, { photo, }, [
    h(listElementStatus, { status, }),
    children,
  ])
}

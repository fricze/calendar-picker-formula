import h from "react-hyperscript"
import styled from "styled-components"

const s = styled.default

export const tableStyle = s.table({
  fontSize: "12px",
  fontFamily: "Open Sans Light",
  margin: 0,
  fontWeight: "normal",
  borderCollapse: "collapse",
})

export const tableHeaderCellStyle = s.th({
  padding: "0 20px",
  height: "50px",
})

export const tableCellStyle = s.td({
  textAlign: "center",
  padding: "0 20px",
})

export const tableRowStyle = s.tr({
  [`&:nth-child(odd)`]: {
    background: "#C6ECF7",
  },
  borderTop: "1px solid #F0F3F6",
  height: "40px",

  // [`${tableCellStyle}:last-child`]: {
  //   paddingRight: "20px",
  // },

  // [`${tableCellStyle}:first-child`]: {
  //   paddingLeft: "20px",
  // },
})

export const tableHeaderStyle = s.thead({
  fontFamily: "Open Sans SemiBold",
  textTransform: "uppercase",
  textAlign: "center",
  color: "#1E2EC6",
  [`${tableHeaderCellStyle}:first-child`]: {
    textAlign: "left",
  },
})

export const tableHeader = ({ children, }) =>
  h(tableHeaderStyle, [h("tr", [children,]),])

const capitalize = s => {
  if (typeof s !== "string") return ""
  return s.charAt(0).toUpperCase() + s.slice(1)
}

const clientStatusSymbolStyle = s.div(({ color, }) => ({
  width: "8px",
  height: "4px",
  background: color,
  borderRadius: "4px",
  marginLeft: "8px",
}))

const clientStatusSymbol = ({ status, color, }) =>
  h(clientStatusSymbolStyle, { color, })

const clientStatusStyle = s.div(({ color, }) => ({
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  color,
}))

const clientStatusColor = status =>
  ({
    done: "#495AFF",
    delayed: "#0ACFFE",
    missed: "#F44236",
  }[status] || "#333")

export const clientStatus = ({ status, }) => {
  const color = clientStatusColor(status)

  return h(clientStatusStyle, { color, }, [
    capitalize(status),
    h(clientStatusSymbol, { status, color, }),
  ])
}

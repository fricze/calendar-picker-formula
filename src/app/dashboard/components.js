import h from "react-hyperscript"

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

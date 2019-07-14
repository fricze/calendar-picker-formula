import React from "react";

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

const SvgText = props => React.createElement(
  "svg",
  _extends({ width: 55, height: 61 }, props),
  React.createElement(
    "g",
    { fill: "none", fillRule: "evenodd" },
    React.createElement("path", {
      fill: "#1C5DA4",
      fillRule: "nonzero",
      d: "M55 61H8V0h32.313L55 14.524z"
    }),
    React.createElement("path", { fill: "#7BB6F6", fillRule: "nonzero", d: "M53 16H39V2z" }),
    React.createElement("rect", { fill: "#2C7ACD", y: 9, width: 32, height: 16, rx: 4 }),
    React.createElement(
      "text",
      {
        fontFamily: "Helvetica",
        fontSize: 17,
        letterSpacing: -0.432,
        fill: "#7BB6F6"
      },
      React.createElement(
        "tspan",
        { x: 2, y: 23 },
        "TXT"
      )
    ),
    React.createElement("path", {
      d: "M39.636 33L41 37.923l-1.116.299c-.262-.492-.53-.964-.806-1.415-.275-.452-.572-.804-.889-1.057a1.95 1.95 0 0 0-1.002-.438 9.433 9.433 0 0 0-1.064-.06h-2.935V47.17c0 .279.014.548.042.807.027.26.137.462.33.608.207.133.49.213.848.24.358.026.73.039 1.116.039V50h-7.048v-1.136c.4 0 .775-.013 1.127-.04.351-.026.63-.106.837-.239a.873.873 0 0 0 .34-.608c.035-.259.052-.528.052-.807V35.252h-2.934c-.358 0-.717.02-1.075.06-.358.04-.696.186-1.012.438-.304.253-.593.605-.868 1.057-.276.451-.545.923-.806 1.415l-1.137-.3L24.364 33h15.272z",
      fill: "#7BB6F6"
    })
  )
);

export default SvgText;

import React from "react";

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

const SvgMsPowerPoint = props => React.createElement(
  "svg",
  _extends({ width: 56, height: 62 }, props),
  React.createElement(
    "g",
    { fill: "none", fillRule: "evenodd" },
    React.createElement("path", { fill: "#E6541D", fillRule: "nonzero", d: "M56 62H8V0h33l15 14.762z" }),
    React.createElement("path", { fill: "#FFA684", fillRule: "nonzero", d: "M54 16H40V2z" }),
    React.createElement("rect", { fill: "#BA3C0D", y: 9, width: 32, height: 16, rx: 4 }),
    React.createElement(
      "text",
      {
        fontFamily: "Helvetica",
        fontSize: 18,
        letterSpacing: -0.432,
        fill: "#FFA684"
      },
      React.createElement(
        "tspan",
        { x: 1, y: 23 },
        "PPT"
      )
    ),
    React.createElement("path", {
      d: "M43 40.331h-9.331V31c1.285 0 2.495.243 3.63.728a9.253 9.253 0 0 1 2.963 2 9.64 9.64 0 0 1 2 2.963A9.001 9.001 0 0 1 43 40.33zm-2.338 2.338c0 .807-.099 1.586-.297 2.338a9.622 9.622 0 0 1-.841 2.133c-.362.67-.8 1.281-1.313 1.835a9.755 9.755 0 0 1-1.712 1.466l-4.491-7.772h8.654zM31.332 52a9.13 9.13 0 0 1-4.481-1.148 9.895 9.895 0 0 1-3.312-3.015l7.587-4.389 4.348 7.568A9.14 9.14 0 0 1 31.33 52zM22 42.669c0-1.299.243-2.516.728-3.65a9.253 9.253 0 0 1 2-2.964 9.64 9.64 0 0 1 2.963-2 9.001 9.001 0 0 1 3.64-.738v8.675l-8.367 4.82a8.636 8.636 0 0 1-.718-1.98A9.502 9.502 0 0 1 22 42.67z",
      fill: "#FFA684"
    })
  )
);

export default SvgMsPowerPoint;

import React from "react";

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

const SvgZip = props => React.createElement(
  "svg",
  _extends({ width: 58, height: 66 }, props),
  React.createElement(
    "defs",
    null,
    React.createElement("path", { id: "zip_svg__b", d: "M56 62H9V0h32.313L56 14.762z" }),
    React.createElement(
      "filter",
      {
        x: "-6.4%",
        y: "-4.8%",
        width: "112.8%",
        height: "109.7%",
        filterUnits: "objectBoundingBox",
        id: "zip_svg__a"
      },
      React.createElement("feOffset", { "in": "SourceAlpha", result: "shadowOffsetOuter1" }),
      React.createElement("feGaussianBlur", {
        stdDeviation: 1,
        "in": "shadowOffsetOuter1",
        result: "shadowBlurOuter1"
      }),
      React.createElement("feColorMatrix", {
        values: "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.5 0",
        "in": "shadowBlurOuter1"
      })
    )
  ),
  React.createElement(
    "g",
    { transform: "translate(0 2)", fill: "none", fillRule: "evenodd" },
    React.createElement(
      "g",
      { fillRule: "nonzero" },
      React.createElement("use", { fill: "#000", filter: "url(#zip_svg__a)", xlinkHref: "#zip_svg__b" }),
      React.createElement("use", { fill: "#FFF", xlinkHref: "#zip_svg__b" })
    ),
    React.createElement("path", { fill: "#585858", fillRule: "nonzero", d: "M54 16H40V2z" }),
    React.createElement("rect", { fill: "#585858", y: 9, width: 32, height: 16, rx: 4 }),
    React.createElement("path", {
      d: "M9.705 21.18H15V23H7v-1.33l5.194-7.834H7.007V12h7.878v1.3l-5.18 7.88zM18 23h-2V12h2v11zm4.152-3.876V23H20V12h4.075c.785 0 1.475.151 2.07.453a3.234 3.234 0 0 1 1.374 1.288c.32.557.481 1.19.481 1.9 0 1.078-.35 1.928-1.051 2.55-.7.622-1.67.933-2.91.933h-1.887zM22 17h2.094c.62 0 1.092-.123 1.418-.368.325-.245.488-.595.488-1.05 0-.468-.164-.847-.492-1.136-.328-.288-.781-.437-1.36-.446H22v3z",
      fill: "#FFF"
    }),
    React.createElement("path", {
      fill: "#585858",
      fillRule: "nonzero",
      d: "M30 41h4v2h-4zM30 34h4v2h-4zM30 28h4v2h-4zM34.75 46h-4.5c0 2.75-2.25 5.5-2.25 7.333C28 55.36 30.013 57 32.5 57s4.5-1.64 4.5-3.667C37 51.5 34.75 48.75 34.75 46zm-2.25 9.167c-1.244 0-2.25-.82-2.25-1.834 0-1.013 1.006-1.833 2.25-1.833s2.25.82 2.25 1.833c0 1.014-1.006 1.834-2.25 1.834z"
    })
  )
);

export default SvgZip;

import React from "react";
import { Navbar } from "react-bootstrap";
import logo from "./logo.svg";

export default function NaviBar() {
  return (
    <Navbar bg="dark" variant="dark">
      <div className="mx-auto">
        <a href="/">
          <img src={logo} alt="Logo" style={{ width: "300px", height: "auto" }} />
        </a>
      </div>
    </Navbar>
  );
}

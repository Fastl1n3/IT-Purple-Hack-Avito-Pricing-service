import React from "react";

import "bootstrap/dist/css/bootstrap.css";

import NaviBar from "./components/Navibar";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MatrixList from "./components/MatrixList";
import BaseLineMatrix from "./BaseLineMatrix";
import DiscountMatrix from "./DiscountMatrix";

export default function App() {
  return (
    <Router>
      <NaviBar/>
      <Routes>
        <Route exact path="/base_matrix/:matrixId" element={<BaseLineMatrix />} />
        <Route exact path="/disc_matrix/:matrixId" element={<DiscountMatrix />} />
        <Route exact path="/" element={<MatrixList />} />
      </Routes>
    </Router>
  );
}
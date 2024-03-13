import React from "react";

import "bootstrap/dist/css/bootstrap.css";

import NaviBar from "./components/Navibar";
import { Col, Container, Row, Card, Button } from "react-bootstrap";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";
import MatrixList from "./components/MatrixList";
import Matrix from  "./Matrix"

export default function App() {
  return (
    <Router>
      <NaviBar/>
      <Routes>
        <Route exact path="/matrix/*" element={<Matrix />} />
        <Route exact path="/" element={<MatrixList />} />
      </Routes>
    </Router>
  );
}
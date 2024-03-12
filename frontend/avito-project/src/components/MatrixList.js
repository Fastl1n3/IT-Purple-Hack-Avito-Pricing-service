import React from "react";

import "bootstrap/dist/css/bootstrap.css";

import NaviBar from "./Navibar";
import { Col, Container, Row, Card, Button } from "react-bootstrap";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";

export default function Matrix() {
  return (
    <>
      <Container style={{ paddingTop: "2rem", paddingBottom: "2rem" }}>
        <h2 style={{ marginBottom: "2rem" }}> Baseline матрицы </h2>
        <Row>
          <Col>
            <Card
              style={{
                width: "20rem",
                height: "20rem",
                backgroundColor: "#F0FBFF",
                border: "1px solid #72D3F5",
              }}
            >
              <Card.Body
                style={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                  padding: "2rem",
                }}
              >
                <Card.Title style={{ fontSize: "1.5rem" }}>Matrix</Card.Title>
                <Card.Text style={{ fontSize: "1.2rem" }}>Date:</Card.Text>
                <Link to="/matrix">
                  <Button variant="primary" style={{ alignSelf: "center" }}>
                    {" "}
                    Посмотреть матрицу{" "}
                  </Button>
                </Link>
              </Card.Body>
            </Card>
          </Col>
        </Row>
        <div style={{ textAlign: "right" }}>
          <Button
            variant="primary"
            style={{
              marginTop: "3rem",
              marginLeft: "65rem",
            }}
          >
            Посмотреть все матрицы
          </Button>
        </div>
      </Container>

      <Container style={{ paddingTop: "2rem", paddingBottom: "2rem" }}>
        <h2 style={{ marginBottom: "2rem" }}> Discount матрицы </h2>
        <Row>
          <Col>
            <Card
              style={{
                width: "20rem",
                height: "20rem",
                backgroundColor: "#F0FBFF",
                border: "1px solid #72D3F5",
              }}
            >
              <Card.Body
                style={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                  padding: "2rem",
                }}
              >
                <Card.Title style={{ fontSize: "1.5rem" }}>Matrix</Card.Title>
                <Card.Text style={{ fontSize: "1.2rem" }}>Date:</Card.Text>
                <Button variant="primary" style={{ alignSelf: "center" }}>
                  {" "}
                  Посмотреть матрицу{" "}
                </Button>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

import React, { useState, useEffect} from "react";

import "bootstrap/dist/css/bootstrap.css";

import NaviBar from "./Navibar";
import { Col, Container, Row, Card, Button } from "react-bootstrap";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";

import { fetchBaselineMatrices } from "../services/requests/BaselineMatrix";
import { fetchDiscountMatrices } from "../services/requests/DiscountMatrix"

export default function Matrix() {
  const [baselineMatrices, setBaselineMatrices] = useState([]);
  const [discountMatrices, setDiscountMatrices] = useState([]);

  useEffect(() => {
    // Функция для загрузки базовых матриц при монтировании компонента
    fetchBaselineMatrices()
      .then(baselineMatrices => {
        setBaselineMatrices(baselineMatrices);
      })
      .catch(error => {
        console.error('Ошибка при загрузке базовых матриц:', error);
      });
  
    // Функция для загрузки матриц скидок при монтировании компонента
    fetchDiscountMatrices()
      .then(discountMatrices => {
        setDiscountMatrices(discountMatrices);
      })
      .catch(error => {
        console.error('Ошибка при загрузке матриц скидок:', error);
      });
  }, []); // Передаем пустой массив зависимостей, чтобы запросы выполнились только один раз при монтировании


  /*
  Пример как разобрать массив:
  baselineMatrices.forEach(matrix => {
    console.log("Matrix ID:", matrix.matrix_id);
    console.log("Segment ID:", matrix.segment_id);
    console.log("Matrix Name:", matrix.matrix_name);
  });
  */
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

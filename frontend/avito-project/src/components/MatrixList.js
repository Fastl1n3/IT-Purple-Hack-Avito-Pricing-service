import React, { useState, useEffect } from "react";

import "bootstrap/dist/css/bootstrap.css";

import NaviBar from "./Navibar";
import { Col, Container, Row, Card, Button } from "react-bootstrap";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";

import { fetchBaselineMatrices } from "../services/requests/BaselineMatrix";
import { fetchDiscountMatrices } from "../services/requests/DiscountMatrix";

export default function Matrix() {
  const [showAllMatrices, setShowAllMatrices] = useState(false);
  const [showAllDiscountMatrices, setShowAllDiscountMatrices] = useState(false);
  const [baselineMatrices, setBaselineMatrices] = useState([
    {
      matrix_id: 1,
      matrix_name: "Matrix A",
    },
    {
      matrix_id: 5,
      matrix_name: "Matrix E",
    },
    {
      matrix_id: 6,
      matrix_name: "Matrix F",
    },
    {
      matrix_id: 7,
      matrix_name: "Matrix G",
    },
    {
      matrix_id: 8,
      matrix_name: "Matrix H",
    },
    {
      matrix_id: 9,
      matrix_name: "Matrix I",
    },
    {
      matrix_id: 10,
      matrix_name: "Matrix J",
    },
  ]);
  const [discountMatrices, setDiscountMatrices] = useState([]);
  const toggleShowAllMatrices = () => {
    setShowAllMatrices(!showAllMatrices);
  };

  const toggleShowAllDiscountMatrices = () => {
    setShowAllDiscountMatrices(!showAllDiscountMatrices);
  };

  const collapseAllMatrices = () => {
    setShowAllMatrices(false);
  };

  const collapseAllDiscountMatrices = () => {
    setShowAllDiscountMatrices(false);
  };

  useEffect(() => {
    // Функция для загрузки базовых матриц при монтировании компонента
    fetchBaselineMatrices()
      .then((baselineMatrices) => {
        setBaselineMatrices(baselineMatrices);
      })
      .catch((error) => {
        console.error("Ошибка при загрузке базовых матриц:", error);
      });

    // Функция для загрузки матриц скидок при монтировании компонента
    fetchDiscountMatrices()
      .then((discountMatrices) => {
        setDiscountMatrices(discountMatrices);
      })
      .catch((error) => {
        console.error("Ошибка при загрузке матриц скидок:", error);
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
        <h2 style={{ marginBottom: "2rem" }}> Discount матрицы </h2>
        <Row>
          {baselineMatrices.slice(0, showAllMatrices ? baselineMatrices.length : 3).map(matrix => (
            <Col key={matrix.matrix_id}>
              <Card
                style={{
                  width: "20rem",
                  maxHeight: "15rem", // Уменьшаем высоту карточек
                  backgroundColor: "#F0FBFF",
                  border: "1px solid #72D3F5",
                  marginBottom: "1rem",
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
                  <Card.Title style={{ fontSize: "1.5rem" }}>{matrix.matrix_name}</Card.Title>
                  <Card.Text style={{ fontSize: "1.2rem" }}>ID: {matrix.matrix_id}</Card.Text>
                  <Link to={`disc_matrix/${matrix.matrix_id}`}>
                    <Button variant="primary" style={{ alignSelf: "center" }}>
                      Посмотреть матрицу
                    </Button>
                  </Link>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
        <div style={{ textAlign: "right", marginBottom: "1rem" }}>
          <Button
            variant="primary"
            style={{ maxWidth: "10rem", marginRight: "1rem" }} // Устанавливаем максимальную ширину для кнопки
            onClick={toggleShowAllMatrices}
          >
            {showAllMatrices ? "Свернуть все матрицы" : "Посмотреть все матрицы"}
          </Button>
          
        </div>
      </Container>

      <Container style={{ paddingTop: "2rem", paddingBottom: "2rem" }}>
        <h2 style={{ marginBottom: "2rem" }}> Baseline матрицы </h2>
        <Row>
          {baselineMatrices.slice(0, showAllDiscountMatrices ? baselineMatrices.length : 3).map(matrix => (
            <Col key={matrix.matrix_id}>
              <Card
                style={{
                  width: "20rem",
                  maxHeight: "15rem", // Уменьшаем высоту карточек
                  backgroundColor: "#F0FBFF",
                  border: "1px solid #72D3F5",
                  marginBottom: "1rem",
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
                  <Card.Title style={{ fontSize: "1.5rem" }}>{matrix.matrix_name}</Card.Title>
                  <Card.Text style={{ fontSize: "1.2rem" }}>ID: {matrix.matrix_id}</Card.Text>
                  <Link to={`/base_matrix/${matrix.matrix_id}`}>
                    <Button variant="primary" style={{ alignSelf: "center" }}>
                      Посмотреть матрицу
                    </Button>
                  </Link>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
        <div style={{ textAlign: "right", marginBottom: "1rem" }}>
          <Button
            variant="primary"
            style={{ maxWidth: "10rem", marginRight: "1rem" }} // Устанавливаем максимальную ширину для кнопки
            onClick={toggleShowAllDiscountMatrices}
          >
            {showAllDiscountMatrices ? "Свернуть все матрицы" : "Посмотреть все матрицы"}
          </Button>
          
        </div>
      </Container>
    </>
  );
}

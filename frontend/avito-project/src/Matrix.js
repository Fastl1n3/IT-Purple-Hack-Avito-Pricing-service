import React from "react";

import { Col, Container, Row, Card, Button, InputGroup } from "react-bootstrap";
import { useState } from "react";
import Table from "./components/Table";
import "./Matrix.css";
import Modal from "./components/Modal";
import Form from "react-bootstrap/Form"

const Matrix = () => {
  const [modalOpen, setModalOpen] = useState(false);
  const [rows, setRows] = useState([
    {
      location: "Новосибирск",
      category: "Животные",
      price: "2500",
    },
    {
      location: "Красноярск",
      category: "Товары для дома",
      price: "500",
    },
    {
      location: "Барнаул",
      category: "Пивоварение",
      price: "1800",
    },
  ]);
  const [rowToEdit, setRowToEdit] = useState(null);

  const handleDeleteRow = (targetIndex) => {
    setRows(rows.filter((_, idx) => idx !== targetIndex));
  };

  const handleEditRow = (idx) => {
    setRowToEdit(idx);

    setModalOpen(true);
  };

  const handleSubmit = (newRow) => {
    rowToEdit === null
      ? setRows([...rows, newRow])
      : setRows(
          rows.map((currRow, idx) => {
            if (idx !== rowToEdit) return currRow;

            return newRow;
          })
        );
  };

  return (
    <>
      {/* <Container style={{ paddingTop: "2rem", paddingBottom: "2rem" }}>
        <Row>
          <Col>
            <h2 style={{ marginBottom: "1rem" }}> Выбранные локации </h2>
          </Col>
          <Col>
            <h2 style={{ marginBottom: "1rem" }}> Выбранные категории </h2>
          </Col>
        </Row>
      </Container> */}
      <div className="Matrix">
        <Table
          rows={rows}
          deleteRow={handleDeleteRow}
          editRow={handleEditRow}
        />
        <button onClick={() => setModalOpen(true)} className="btn">
          Add
        </button>
        {modalOpen && (
          <Modal
            closeModal={() => {
              setModalOpen(false);
              setRowToEdit(null);
            }}
            onSubmit={handleSubmit}
            defaultValue={rowToEdit !== null && rows[rowToEdit]}
          />
        )}
      </div>
    </>
  );
};

export default Matrix;

import React, { useState } from "react";
import { BsFillTrashFill, BsFillPencilFill } from "react-icons/bs";
import Form from "react-bootstrap/Form";
import "./Table.css";
import { InputGroup } from "react-bootstrap";
import {Row, Col} from "react-bootstrap"
const Table = ({ rows, deleteRow, editRow }) => {
  const [searchLocation, setSearchLocation] = useState("");
  const [searchCategory, setSearchCategory] = useState("");

  const filteredRows = rows.filter(
    (row) =>
      row.location.toLowerCase().includes(searchLocation.toLowerCase()) &&
      row.category.toLowerCase().includes(searchCategory.toLowerCase())
  );

  const handleLocationSearch = (event) => {
    setSearchLocation(event.target.value);
  };

  const handleCategorySearch = (event) => {
    setSearchCategory(event.target.value);
  };

  return (
    <div className="table-wrapper">
      <div style={{ display: "flex", justifyContent: "center" }}>
        <Row>
          <Col>
            <Form>
              <InputGroup>
                <Form.Control
                  type="text"
                  placeholder="Поиск по локациям"
                  value={searchLocation}
                  onChange={handleLocationSearch}
                  style={{ margin: "5px" }}
                />
              </InputGroup>
            </Form>
          </Col>
          <Col>
            <Form>
              <InputGroup>
                <Form.Control
                  type="text"
                  placeholder="Поиск по категориям"
                  value={searchCategory}
                  onChange={handleCategorySearch}
                  style={{ margin: "5px", width: "100%" }}
                />
              </InputGroup>
            </Form>
          </Col>
        </Row>
      </div>

      <table className="table">
        <thead>
          <tr>
            <th className="expand"> Локация</th>
            <th className="expand"> Категория</th>
            <th className="expand"> Цена</th>
            <th className="expand"> Действия</th>
          </tr>
        </thead>
        <tbody>
          {filteredRows.map((row, idx) => (
            <tr key={idx}>
              <td>{row.location}</td>
              <td className="expand">{row.category}</td>
              <td className="expand">{row.price}</td>
              <td className="fit">
                <span className="actions">
                  <BsFillTrashFill
                    className="delete-btn"
                    onClick={() => deleteRow(idx)}
                  />
                  <BsFillPencilFill
                    className="edit-btn"
                    onClick={() => editRow(idx)}
                  />
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table;

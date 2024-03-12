import React from "react";

import { BsFillTrashFill, BsFillPencilFill } from "react-icons/bs";

import "./Table.css";

export const Table = ({ rows, deleteRow, editRow }) => {
  return (
    <div className="table-wrapper">
      <table className="table">
        <thead>
          <tr>
            <th className="expand"> Локация</th>
            <th className="expand"> Категория</th>
            <th className="expand"> Цена</th>
            <th className = "expand">  Действия</th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, idx) => {
            
            return (
              <tr key={idx}>
                <td>{row.location}</td>
                <td className="expand">{row.category}</td>
                <td className="expand">{row.price}
             
                </td>
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
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
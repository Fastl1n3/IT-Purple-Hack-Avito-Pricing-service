import React, { useState } from "react";

import "./Modal.css";

export const Modal = ({ closeModal, onSubmit, defaultValue }) => {
  const [formState, setFormState] = useState(
    defaultValue || {
      location: "",
      category: "",
      price: "",
    }
  );
  const [errors, setErrors] = useState("");

  const validateForm = () => {
    if (formState.location && formState.category && formState.price) {
      setErrors("");
      return true;
    } else {
      let errorFields = [];
      for (const [key, value] of Object.entries(formState)) {
        if (!value) {
          errorFields.push(key);
        }
      }
      setErrors(errorFields.join(", "));
      return false;
    }
  };

  const handleChange = (e) => {
    setFormState({ ...formState, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) return;

    onSubmit(formState);

    closeModal();
  };

  return (
    <div
      className="modal-container"
      onClick={(e) => {
        if (e.target.className === "modal-container") closeModal();
      }}
    >
      <div className="my-modal">
        <form>
          <div className="form-group">
            <label htmlFor="location">Локация</label>
            <input name="location" onChange={handleChange} value={formState.location} />
          </div>
          <div className="form-group">
            <label htmlFor="category">Категория</label>
            <input name="category" onChange={handleChange} value={formState.category} />
          </div>
          <div className="form-group">
            <label htmlFor="price">Цена</label>
            <input name="price" onChange={handleChange} value={formState.price} />
          </div>
          {errors && <div className="error">{`Please include: ${errors}`}</div>}
          <button type="submit" className="btn" onClick={handleSubmit}>
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default Modal;

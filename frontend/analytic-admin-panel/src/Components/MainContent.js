import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const MainContent = () => {
    return (
      <div>
        <h1>Выберите страницу:</h1>
        <ul>
          <li><Link to="/baseline">Baseline</Link></li>
          <li><Link to="/discount">Discount</Link></li>
          <li><Link to="/category">Category</Link></li>
        </ul>
      </div>
    );
};

export default MainContent;
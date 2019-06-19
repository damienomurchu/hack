import React, { Component } from 'react';
import logo from '../assets/images/mwgLogoWhite.png';
import './Header.css';

import Navbar from 'react-bootstrap/Navbar';

class Header extends Component {
  render() {
    return (
      <div className="mainHeader">
        <Navbar bg="success" fixed="top">
          <Navbar.Brand href="#home">
            <img src={logo} height="90" className="d-inline-block align-top" alt="MWG logo" />
          </Navbar.Brand>
        </Navbar>
      </div>
    );
  }
}

export default Header;

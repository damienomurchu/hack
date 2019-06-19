import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image';
import background from '../assets/images/OFC16_Cafe_018_1600.png';
import './Background.css';

class Background extends Component {
  render() {
    return (
      <Container className="background">
        <Image className="image" src={background} fluid />
      </Container>
    );
  }
}

export default Background;

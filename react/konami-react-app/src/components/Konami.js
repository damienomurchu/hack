import React, { Component } from 'react';
import Mousetrap from 'mousetrap';

class Konami extends Component {

  constructor() {
    super();
  }  

  componentDidMount() {
    console.log('mounted');
    Mousetrap.bind(['1 2 3 4', 'up up down down left right left right b a'], this.popUp);
  }

  componentWillUnmount() {
    Mousetrap.unbind(['1 2 3 4', 'up up down down left right left right b a']);    
  }

  popUp() {
    console.log('popUp called');
    alert('The Konami code happened!');
  }

  render() {
    return (
      <div>Hello, this is {this.props.name}!</div>
    );
  }
}

export default Konami;

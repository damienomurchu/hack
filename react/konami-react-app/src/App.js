import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Konami from './components/Konami.js';

class App extends Component {

  constructor() {
    super();
    this.state = {
      activated: false
    };
    this.toggleActivation = this.toggleActivation.bind(this);
  }

  toggleActivation() {
    if (this.state.activated === true) {
      this.setState({ activated: false });
    } else {
      this.setState({ activated: true});
    }
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <button onClick={this.toggleActivation}>Toggle Konami Code!</button>
        {this.state.activated ? <Konami name="Damien"/> : <div>Konami Code: Deactivated</div>}
      </div>
    );
  }
}

export default App;

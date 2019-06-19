import React, { Component } from 'react';
import { connect } from 'react-redux';

import './App.css';
import MainPage from './components/MainPage';

class App extends Component {

  render() {
    return (
      <div className="App">
        <MainPage />
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    modal: state.currentModal
  };
};

const mapDispatchToProps = (dispatch) => {
  return {};
};

export default connect(mapStateToProps, mapDispatchToProps)(App);

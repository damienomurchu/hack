import React, { Component } from 'react';
import { connect } from 'react-redux';

import Header from './Header';
import Background from './Background';
import Welcome from './Welcome';
import GroceryForm from './GroceryForm';
import Confirmation from './Confirmation';

class MainPage extends Component {
  render() {
    const modalToDisplay = this.props.modal;
    let modal;
    switch (modalToDisplay) {
      case 'welcome':
        modal = <Welcome />;
        break;
      case 'form':
        modal = <GroceryForm />;
        break;
      case 'confirmation':
        modal = <Confirmation />;
        break;
      default:
        modal = <Welcome />;
        break;
    }

    return (
      <div className="mainPage">
        <Header />
        <Background />
        {modal}
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    modal: state.currentModal,
    showWelcome: state.showWelcome,
    showForm: state.showForm,
    showConfirmation: state.showConfirmation
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    openWelcome: () => {
      dispatch({ type: 'OPEN_WELCOME_MODAL' });
    }
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(MainPage);

import React, { Component } from 'react';
import { connect } from 'react-redux';

import './Confirmation.css';
import Modal from 'react-bootstrap/Modal';

class Confirmation extends Component {
  render() {
    return (
      <Modal show={this.props.show} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body className="confirmationModal">
          <p>Thank You</p>
        </Modal.Body>
      </Modal>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    show: state.showConfirmation
  };
};

const mapDispatchToProps = (dispatch) => {
  return {};
};

export default connect(mapStateToProps, mapDispatchToProps)(Confirmation);

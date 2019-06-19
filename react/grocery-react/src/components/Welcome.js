import React, { Component } from 'react';
import { connect } from 'react-redux';

import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

import './Welcome.css';

class Welcome extends Component {
  render() {
    return (
      <Modal show={this.props.show} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body className="welcomeModal">
          <h1>Welcome</h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipiscing elit, facilisi condimentum ridiculus tempus magnis est,
            vel elementum hac dapibus ultricies dis. Potenti quisque tristique cum placerat fames nulla ac, condimentum
            magna posuere porttitor.
          </p>
          <p>
            Ligula morbi euismod massa molestie auctor fusce sociosqu pretium id, justo etiam potenti commodo posuere
            vulputate in mollis eu et, mattis congue scelerisque montes ante volutpat suspendisse vitae. Sagittis tortor
            orci a arcu pulvinar sem vivamus litora, tempor parturient sed elementum habitant cras. Varius eros aptent
            luctus mattis ante at tempus consequat tincidunt ac class nisi inceptos curabitur odio, taciti blandit nunc
            nisl mauris nascetur sed orci urna arcu cum tellus.
          </p>
          <Button className="welcomeButton" onClick={this.props.nextScreen} variant="light" size="lg">
            Next
          </Button>
        </Modal.Body>
      </Modal>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    show: state.showWelcome
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    nextScreen: () => {
      dispatch({ type: 'OPEN_FORM_MODAL' });
    }
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Welcome);

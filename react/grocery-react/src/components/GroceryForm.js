import React, { Component } from 'react';
import { connect } from 'react-redux';

import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import './GroceryForm.css';

import { Formik } from 'formik';
import * as Yup from 'yup';

const schema = Yup.object({
  name: Yup.string().max(20).required('What should we call you?'),
  surname: Yup.string().max(20).required('Do you have a last name?'),
  dob: Yup.date().required('We need to know your age'),
  nationality: Yup.string().max(20).required('Tell us your nationality'),
  email: Yup.string().max(50).email().required('Email is required to contact you'),
  occupation: Yup.string().max(30).required('We need to know your occupation')
});

class GroceryForm extends Component {
  render() {
    return (
      <Modal show={this.props.show} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body className="formModal">
          <Formik
            validationSchema={schema}
            onSubmit={this.props.submitForm}
            initialValues={{
              name: '',
              surname: '',
              dob: '',
              nationality: '',
              email: '',
              occupation: ''
            }}
          >
            {({ handleSubmit, handleChange, handleBlur, values, touched, isValid, errors }) => (
              <Form noValidate onSubmit={handleSubmit}>
                <Row>
                  <Col>
                    <Form.Group controlId="name">
                      <Form.Label>First Name</Form.Label>
                      <Form.Control
                        type="text"
                        name="name"
                        placeholder="First Name"
                        value={values.name}
                        onChange={handleChange}
                        isValid={touched.name && !errors.name}
                        isInvalid={!!errors.name}
                      />
                      <Form.Control.Feedback type="invalid">{errors.name}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group controlId="surname">
                      <Form.Label>Surname</Form.Label>
                      <Form.Control
                        type="text"
                        name="surname"
                        placeholder="Surname"
                        value={values.surname}
                        onChange={handleChange}
                        isValid={touched.surname && !errors.surname}
                        isInvalid={!!errors.surname}
                      />
                      <Form.Control.Feedback type="invalid">{errors.surname}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group controlId="dob">
                      <Form.Label>Date of Birth</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Date of Birth"
                        name="dob"
                        value={values.dob}
                        onChange={handleChange}
                        isValid={touched.dob && !errors.dob}
                        isInvalid={!!errors.dob}
                      />
                      <Form.Control.Feedback type="invalid">{errors.dob}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group controlId="nationality">
                      <Form.Label>Nationality</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Nationality"
                        aria-describedby="inputGroupPrepend"
                        name="nationality"
                        value={values.nationality}
                        onChange={handleChange}
                        isValid={touched.nationality && !errors.nationality}
                        isInvalid={!!errors.nationality}
                      />
                      <Form.Control.Feedback type="invalid">{errors.nationality}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group controlId="email">
                      <Form.Label>Email address</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Email"
                        aria-describedby="inputGroupPrepend"
                        name="email"
                        value={values.email}
                        onChange={handleChange}
                        isValid={touched.email && !errors.email}
                        isInvalid={!!errors.email}
                      />
                      <Form.Control.Feedback type="invalid">{errors.email}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group controlId="occupation">
                      <Form.Label>Occupation</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Occupation"
                        aria-describedby="inputGroupPrepend"
                        name="occupation"
                        value={values.occupation}
                        onChange={handleChange}
                        isValid={touched.occupation && !errors.occupation}
                        isInvalid={!!errors.occupation}
                      />
                      <Form.Control.Feedback type="invalid">{errors.occupation}</Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Button className="formButton" type="submit" variant="success">
                      Submit
                    </Button>
                  </Col>
                </Row>
              </Form>
            )}
          </Formik>
        </Modal.Body>
      </Modal>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    show: state.showForm
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    submitForm: (values) => {
      console.log(JSON.stringify(values, null, 2)); // this is where you would persist values to a db or send to a backend
      dispatch({ type: 'OPEN_CONFIRMATION_MODAL' });
    }
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(GroceryForm);

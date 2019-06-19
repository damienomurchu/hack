import React from 'react';
import ReactDOM from 'react-dom';

import { createStore } from 'redux';
import { Provider } from 'react-redux';

import './index.css';
import App from './App';

const openModal = (state, action) => {
  switch (action.type) {
    case 'OPEN_WELCOME_MODAL':
      return {
        currentModal: 'welcome',
        showWelcome: true,
        showForm: false,
        showConfirmation: false
      };
    case 'OPEN_FORM_MODAL':
      return {
        currentModal: 'form',
        showWelcome: false,
        showForm: true,
        showConfirmation: false
      };
    case 'OPEN_CONFIRMATION_MODAL':
      return {
        currentModal: 'confirmation',
        showWelcome: false,
        showForm: false,
        showConfirmation: true
      };
    default:
      return state;
  }
};

const initialState = {
  currentModal: 'welcome',
  showWelcome: true,
  showForm: false,
  showConfirmation: false
};
const store = createStore(openModal, initialState);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);

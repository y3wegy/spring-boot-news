import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import Login from './container/LoginPage.jsx';

import './css/App.css';

const render = Component => {
  ReactDOM.render(
      <AppContainer>
        <Component/>
      </AppContainer>,
      document.getElementById('Root'),
  );
};

render(Login);

if (module.hot) {
  module.hot.accept('./container/LoginPage', () => {
    render(Login);
  });
}
import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import LoginPage from './container/LoginPage.jsx';

import './css/App.css';

const render = Component => {
  ReactDOM.render(
      <AppContainer>
        <Component/>
      </AppContainer>,
      document.getElementById('Root'),
  );
};

render(LoginPage);

if (module.hot) {
  module.hot.accept('./container/LoginPage', () => {
    render(LoginPage);
  });
}
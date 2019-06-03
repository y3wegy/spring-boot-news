import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import Login from './container/Login';

import './css/App.css';

const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Component/>
        </AppContainer>,
        document.getElementById('Root')
    );
}

render(Login);

if (module.hot) {
    module.hot.accept('./container/Login', () => {
        render(Login)
    });
}
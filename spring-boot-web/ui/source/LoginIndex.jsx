import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import Login from './component/business/Login';

import './component/business/App.css';

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
    module.hot.accept('./component/business/Login', () => {
        render(Login)
    });
}
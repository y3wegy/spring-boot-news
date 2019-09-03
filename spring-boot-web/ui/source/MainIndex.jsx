import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import MainPage from './container/MainPage.jsx';

import './css/App.css';

const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Component/>
        </AppContainer>,
        document.getElementById('Root'),
    );
};

render(MainPage);
if (module.hot) {
    module.hot.accept('./container/MainPage', () => {
        render(MainPage);
    });
}
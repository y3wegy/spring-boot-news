import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import MainPage from './component/business/MainPage';

import './component/business/App.css';

const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Component/>
        </AppContainer>,
        document.getElementById('Root')
    );
}

render(MainPage);
if (module.hot) {
    module.hot.accept('./component/business/MainPage', () => {
        render(MainPage)
    });
}
import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import Main from './component/business/Main';

import './component/business/App.css';

const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Component/>
        </AppContainer>,
        document.getElementById('Root')
    );
}

render(Main);
if (module.hot) {
    module.hot.accept('./component/business/Main', () => {
        render(Main)
    });
}
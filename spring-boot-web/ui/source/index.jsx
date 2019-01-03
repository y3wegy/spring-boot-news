import React from 'react';
import ReactDOM from 'react-dom';
import MixIndex from './component/MixIndex';
import {AppContainer, hot} from 'react-hot-loader';
//import * as OfflinePluginRuntime from 'offline-plugin/runtime';


const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Component/>
        </AppContainer>,
        document.getElementById('Root')
    );
}

render(MixIndex);

if (module.hot) {
    module.hot.accept('./component/MixIndex', () => {
        render(MixIndex)
    });
}
/*
ReactDOM.render(<App/>,
    document.getElementById('Root')
)
//OfflinePluginRuntime.install();
if (module.hot) {
    module.hot.accept();
}*/

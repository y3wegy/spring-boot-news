import React from 'react';
import Websocket from 'react-websocket';
import Notification from '../../commmon/Notification';

const PUSH_PATH = 'ws://localhost:8888/api/hello';

export default class Push extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            count: 90,
        };
    }

    onMessage = (data) => {
        let result = JSON.parse(data);
        Notification.success(result);
    };

    onOpen = () => {
        Notification.info({
            message: 'Push Service',
            description: 'Connection push Service successfully',
        });
    };

    onClose = () => {
        Notification.info({
            message: 'Push Service',
            description: 'Close push Service successfully',
        });
    };

    sendMessage = (msg) => {
        this.clientRef.sendMessage('/topics/all', msg);
    }

    render() {
        return (
            <div>
                <Websocket url={PUSH_PATH}
                           onMessage={this.onMessage}
                           onOpen={this.onOpen}
                           onClose={this.onClose}/>
            </div>
        );
    }
}
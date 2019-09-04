import React from 'react';
import SockJsClient from 'react-stomp';
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
        let message = JSON.parse(data);
        Notification.info({
            message: message.topic,
            description:
                JSON.stringify(message.msg),
        });
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
                <SockJsClient url='http://localhost:9601/endpoint/websocketJS'
                              topics={['/topics/greetings', 'topic/hello', 'topic/own', 'topic/callBack']}
                              onConnect={this.onOpen} onDisconnect={this.onClose}
                              onMessage={this.onMessage}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
            </div>
            < /div>
        );
    }
}
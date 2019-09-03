import React, {Component} from 'react';
import '@babel/polyfill';
import {Button, Carousel, Popconfirm} from 'antd';
import UploadFile from '../business/upload/UploadFile';
import Notification from '../../commmon/Notification';
import Logo from '../../resources/logo.svg';
import '../../css/App.css';
import Chrysanthemum from '../../resources/Chrysanthemum.jpg';
import Desert from '../../resources/Desert.jpg';
import Hydrangeas from '../../resources/Hydrangeas.jpg';
import Jellyfish from '../../resources/Jellyfish.jpg';

const verifyTitle = 'user (y3wegy) verify result';
export default class DefaultContent extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App">
                <h1 className="App-title">Welcome to React</h1>
                <header className="App-header">
                    <Logo className="App-logo" alt="logo"/>
                </header>
                <Carousel autoplay>
                    <div>
                        <img src={Chrysanthemum}/>
                    </div>
                    <div>
                        <img src={Desert}/>
                    </div>
                    <div>
                        <img src={Hydrangeas}/>
                    </div>
                    <div>
                        <img src={Jellyfish}/>
                    </div>
                </Carousel>
                <Popconfirm title="Welcome" okText="test" autoAdjustOverflow={true}>
                    <Button>Delete</Button>
                </Popconfirm>
                <br/>
                <UploadFile/>
            </div>
        );
    }

    componentDidMount() {
        this.callApi().then(res => {
            Notification.success({
                message: verifyTitle,
                description:
                    JSON.stringify(res.data),
            });
        }).catch(err => {
            console.error(err);
            Notification.error({
                message: verifyTitle,
                description: err.message,
            });
        });
    }

    callApi = async () => {
        const response = await fetch('/api/hello?name=y3wegy',
            {method: 'post', body: JSON.stringify({'Age': '28'})});
        const body = await response.json();

        if (!response.ok) {
            throw Error(response.statusText);
        }
        if (response.status !== 200) {
            throw Error(body.message);
        }

        return body;
    };
}

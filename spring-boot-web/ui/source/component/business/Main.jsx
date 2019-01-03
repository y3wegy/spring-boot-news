import React, {Component} from 'react';
import "@babel/polyfill";
import {LocaleProvider, DatePicker, message, Popconfirm, Button} from 'antd';
import Logo from './logo.svg';
import './App.css';

export default class Main extends Component {

    constructor(props) {
        super(props);
        this._initState = {
            response: ''
        };
        this.state = this._initState;
    }

    componentDidMount() {
        message.success('Welcome Login.');
        /*fetch('/api/hello?name=y3wegy')
            .then(response => response.json())
            .then(res => {
                console.info(res);
                this.setState({response: res.response})
            })
            .catch(err => console.log(err));*/
        this.callApi()
            .then(res => this.setState({response: res.response}))
            .catch(err => console.log(err));
    }

    callApi = async () => {
        const response = await fetch('/api/hello?name=y3wegy',
            {method: 'post', body: JSON.stringify({'Age': '28'})});
        const body = await response.json();

        if (!response.ok) {
            message.error(response.statusText);
            throw Error(response.statusText);
        }
        if (response.status !== 200) {
            throw Error(body.message);
        }

        return body;
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <Logo className="App-logo" alt="logo"/>
                </header>
                <h1 className="App-title">Welcome to React</h1>
                <p className="App-intro">{JSON.stringify(this.state.response)}</p>
                <Popconfirm title="Welcome" okText="test" autoAdjustOverflow={true}>
                    <Button>Delete</Button>
                </Popconfirm>
            </div>
        );
    }
}

import React, {Component} from 'react';
import '@babel/polyfill';
import {Button, Carousel, message, Popconfirm} from 'antd';
import UploadFile from '../business/upload/UploadFile';
import Logo from '../../resources/logo.svg';
import '../../css/App.css';
import Notification from '../../commmon/Notification';
import Chrysanthemum from '../../resources/Chrysanthemum.jpg';
import Desert from '../../resources/Desert.jpg';
import Hydrangeas from '../../resources/Hydrangeas.jpg';
import Jellyfish from '../../resources/Jellyfish.jpg';

export default class DefaultContent extends Component {

  constructor(props) {
    super(props);
    this._initState = {
      response: '',
    };
    this.state = this._initState;
  }

  componentDidMount() {
    Notification.success({
      duration: 1,
      message: 'Login',
      description:
          'Welcome Login.',
    });
    this.callApi().
        then(res => this.setState({response: res.response})).
        catch(err => console.log(err));
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
}

import React, {Component} from 'react';
import '@babel/polyfill';
import {Button, Checkbox, Form, Icon, Input, message} from 'antd';
import {defaultInstance, jsonInstance} from '../../HttpRequest';
import './App.css';

const FormItem = Form.Item;

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',  // 当前输入的用户名
      password: '',  // 当前输入的密码
      requesting: false, // 当前是否正在请求服务端接口
    };
  }

  componentDidMount = () => {
    defaultInstance.request({
      url: '/api/hello',
      method: 'post',
      params: {
        'name': 'y3wegy',
      },
      data: {
        'Age': '28',
      },
    });
  };

  // controlled components

  handleUsernameInput = (e) => {
    this.setState({username: e.target.value});
  };

  handlePasswordInput = (e) => {
    this.setState({password: e.target.value});
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        //fetch("/api/user/login", {method: "post", body: JSON.stringify(values)})
        jsonInstance.post('/api/user/login', values)
            .then(response => response.data)
            .then(response => {
              if (response.isSuccess === 'true') {
                window.location.href = 'main.html';
              } else {
                message.error('Login failed.' + response.date);
              }
            }).
            catch(error => message.error('Login failed.' + error));
      }
    });
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
        <Form onSubmit={this.handleSubmit} className="login-form">
          <FormItem>
            {getFieldDecorator('userName', {
              rules: [{required: true, message: 'Please input your username!'}],
            })(
                <Input prefix={<Icon type="user"
                                     style={{color: 'rgba(0,0,0,.25)'}}/>}
                       placeholder="Username"/>,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{required: true, message: 'Please input your Password!'}],
            })(
                <Input prefix={<Icon type="lock"
                                     style={{color: 'rgba(0,0,0,.25)'}}/>}
                       type="password"
                       placeholder="Password"/>,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true,
            })(
                <Checkbox>Remember me</Checkbox>,
            )}
            <a className="login-form-forgot" href="">Forgot password</a>
            <Button type="primary" htmlType="submit"
                    className="login-form-button">
              Log in
            </Button>
            Or <a href="">register now!</a>
          </FormItem>
        </Form>
    );

  }
}

export default Form.create()(Login);
import React, {Component} from 'react';
import {Layout, Menu} from 'antd';
import Logo from './logo.svg';

const {SubMenu, ItemGroup} = Menu;
const {Header, Content, Sider} = Layout;

export default class SimpleHeader extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapsed: false,
      mode: 'inline',
      keyPath: [],

    };
  }

  render() {
    const {headerProps} = this.props;
    return (
        <Header className="header">
          <div className="logo">
            <Logo className="App-logo" alt="logo" style={{height: '64px'}}/>
          </div>
          <Menu
              theme="dark"
              mode="horizontal"
              defaultSelectedKeys={['1']}
              style={{lineHeight: '64px'}}
              {...headerProps}
          >
            <Menu.Item key="1">nav 1</Menu.Item>
            <Menu.Item key="2">nav 2</Menu.Item>
            <Menu.Item key="3">nav 3</Menu.Item>
          </Menu>
        </Header>
    );
  }
}

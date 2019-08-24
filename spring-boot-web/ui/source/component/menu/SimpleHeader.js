import React, {Component} from 'react';
import {Layout, Menu} from 'antd';
import Logo from '../../resources/logo.svg';

const {SubMenu, ItemGroup} = Menu;
const {Header, Content} = Layout;

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
            <SubMenu
                title='nav1'
            >
              <ItemGroup
                  title='group1'>
                <Menu.Item key='1.1'>nav1.1</Menu.Item>
                <Menu.Item key='1.2'>nav1.2</Menu.Item>
                <Menu.Item key='1.3'>nav1.3</Menu.Item>
              </ItemGroup>
              <SubMenu
                  title='nav2'>
                <Menu.Item key='1.4'>nav1.4</Menu.Item>
                <Menu.Item key='1.5'>nav1.5</Menu.Item>
                <Menu.Item key='1.6'>nav1.6</Menu.Item>
              </SubMenu>
            </SubMenu>
            <Menu.Item key="2">nav 2</Menu.Item>
            <Menu.Item key="3">nav 3</Menu.Item>
            <Menu.Item key="4">nav 4</Menu.Item>
          </Menu>
        </Header>
    );
  }
}

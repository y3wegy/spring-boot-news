import React, {Component} from 'react';
import {Icon, Layout, Menu} from 'antd';
import {NavLink} from 'react-router-dom';

const {SubMenu, ItemGroup} = Menu;
const {Sider} = Layout;

export default class SimpleSiderNav extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapsed: false,
      mode: 'inline',
      keyPath: [],

    };
  }

  onCollapse = (collapsed) => {
    console.log(collapsed);
    this.setState({
      collapsed,
      mode: collapsed ? 'vertical' : 'inline',
    });
  };

  render() {
    const {siderProps} = this.props;
    return (
        <Sider width={200} style={{background: '#fff'}}
               collapsible
               collapsed={this.state.collapsed}
               onCollapse={this.onCollapse}
        >
          <Menu
              theme="dark"
              mode="inline"
              defaultSelectedKeys={['1']}
              defaultOpenKeys={['sub1']}
              style={{height: '100%'}}
              {...siderProps}
          >
            <SubMenu key="subnav 1"
                     title={<span><Icon type="user"/>subnav 1</span>}>
              <Menu.Item key="option1">option1</Menu.Item>
              <Menu.Item key="option2">option2</Menu.Item>
              <Menu.Item key="option3">option3</Menu.Item>
              <Menu.Item key="option4">option4</Menu.Item>
            </SubMenu>
            <SubMenu key="subnav 2"
                     title={<span><Icon type="laptop"/>subnav 2</span>}>
              <Menu.Item key="option5">option5</Menu.Item>
              <Menu.Item key="option6">option6</Menu.Item>
              <Menu.Item key="option7">option7</Menu.Item>
              <Menu.Item key="option8">option8</Menu.Item>
            </SubMenu>
            <SubMenu key="subnav 3"
                     title={<span><Icon
                         type="notification"/>subnav 3</span>}>
              <Menu.Item key="option9">option9</Menu.Item>
              <Menu.Item key="option10">option10</Menu.Item>
              <Menu.Item key="option11">option11</Menu.Item>
              <Menu.Item key="option12">option12</Menu.Item>
            </SubMenu>
            <SubMenu key='Setting'
                     title={<span><Icon type="setting"/>Setting</span>}>
              <ItemGroup title="Setting1" key='Setting1'>
                <Menu.Item key="setting1.1">Setting1.1</Menu.Item>
                <Menu.Item key="setting1.2">Setting1.2</Menu.Item>
              </ItemGroup>
              <ItemGroup title="Setting2" key='Setting2'>
                <Menu.Item key="Setting2.1">Setting2.1</Menu.Item>
                <Menu.Item key="Setting2.2">Setting2.2</Menu.Item>
              </ItemGroup>
            </SubMenu>
            <SubMenu key='Mail'
                     title={<span><Icon type="mail"/>Mail</span>}>
              <SubMenu title="Mail1" key="Mail1">
                <Menu.Item key="Mail1.1">Mail1.1</Menu.Item>
                <Menu.Item key="Mail1.2">Mail1.2</Menu.Item>
              </SubMenu>
              <SubMenu title='Mail2' key='Mail2'>
                <Menu.Item key="Mail2.1">Mail2.1</Menu.Item>
                <Menu.Item key="Mail2.2">
                  <NavLink to='/Mail2.2'>Mail2.2</NavLink>
                </Menu.Item>
              </SubMenu>
            </SubMenu>
          </Menu>
        </Sider>
    );
  }
}

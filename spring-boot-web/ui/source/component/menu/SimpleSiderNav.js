import React, {Component} from 'react';
import {Layout, Menu} from 'antd';
import {NavLink} from 'react-router-dom';
import {JsonAxiosRequest} from '../../commmon/HttpRequest';
import Notification from '../../commmon/Notification';

const {SubMenu, ItemGroup} = Menu;
const {Sider} = Layout;

const loadMenuTitle = 'Load Menu';

export default class SimpleSiderNav extends Component {

  constructor(props) {
    super(props);
    this.state = {
      collapsed: false,
      mode: 'inline',
      menuData: [],
    };
  }

  onCollapse = (collapsed) => {
    console.log(collapsed);
    this.setState({
      collapsed,
      mode: collapsed ? 'vertical' : 'inline',
    });
  };
  buildMenuComponent = (menuConfig) => {
    if (menuConfig.length == 0) {
      return (<div></div>);
    }
    return menuConfig.map(menu => this.buildMenuNode(menu));
  };
  buildMenuNode = (menuConfig) => {
    if (menuConfig == undefined) {
      return <div></div>;
    }

    if (menuConfig.childMenuList !== undefined &&
        menuConfig.childMenuList.length > 0) {
      const subMenuProp = {
        key: menuConfig.menuCd,
        title: menuConfig.displayName,
        disabled: menuConfig.disabled,
      };
      return (<SubMenu {...subMenuProp}>
        {menuConfig.childMenuList.map(
            subMenuConfig => this.buildMenuNode(subMenuConfig))}
      </SubMenu>);
    } else {
      return this.buildLeafMenuNode(menuConfig);
    }
  };
  buildLeafMenuNode = (menuConfig) => {
    const menuItemProp = {
      key: menuConfig.menuCd,
      disabled: menuConfig.disabled,
    };
    return (<Menu.Item {...menuItemProp}>
      {/*<NavLink to={menuConfig.menuCd}>{menuConfig.displayName}</NavLink>*/}
      {menuConfig.displayName}
    </Menu.Item>);
  };

  render() {
    const {...menuProps} = this.props.siderProps;
    const {menuData} = this.state;
    if (menuData.length == 0) {
      return (<div></div>);
    }

    return (
        <Sider width={200} style={{background: '#fff'}}
               collapsible
               collapsed={this.state.collapsed}
               onCollapse={this.onCollapse}
        >
          <Menu
              theme="dark"
              mode="inline"
              //defaultSelectedKeys={openKeyPath}
              //defaultOpenKeys={['sub1']}
              style={{height: '100%'}}
              {...menuProps}
          >
            {this.buildMenuComponent(menuData)}
          </Menu>
        </Sider>
    );
  }

  componentDidMount = () => {
    JsonAxiosRequest.post('/api/web/menu').
        then(response => response.data).
        then(responseJson => {
          if (responseJson.isSuccess) {
            const menuData = JSON.parse(responseJson.data);
            this.setState({menuData});
            Notification.success({
              message: loadMenuTitle,
              description:
                  'load menu Data successfully',
            });
          } else {
            Notification.error({
              message: loadMenuTitle,
              description:
                  'load menu Data failed',
            });
          }
        }).
        catch(error => {
          console.error(error);
          Notification.error({
            message: loadMenuTitle,
            description:
                error.response.statusText,
          });
        });
  };
}

import React, {Component} from 'react';
import {Layout, Menu} from 'antd';
import {NavLink} from 'react-router-dom';

const {SubMenu, ItemGroup} = Menu;
const {Sider} = Layout;

export default class SimpleSiderNav extends Component {
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
      <NavLink to={menuConfig.menuCd}>{menuConfig.displayName}</NavLink>
    </Menu.Item>);
  };
  /**
   * menuData is array
   * @param menuData
   */
  setDefaultOpenMenu = (menuData) => {
    let openKeyPath = [];
    //let defaultSelectKey;

    if (menuData && menuData.length > 0) {
      let currentMenuItem = menuData[0];
      while (currentMenuItem.childMenuList !== undefined &&
      currentMenuItem.childMenuList.length > 0) {
        openKeyPath.push(currentMenuItem.menuCd);
        currentMenuItem = currentMenuItem.childMenuList[0];
      }
      //defaultSelectKey = currentMenuItem.menuCd;
    }
    this.setState({openKeyPath});
  };

  constructor(props) {
    super(props);
    this.state = {
      collapsed: false,
      mode: 'inline',
    };
  }

  render() {
    const {menuData, ...menuProps} = this.props.siderProps;
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
}

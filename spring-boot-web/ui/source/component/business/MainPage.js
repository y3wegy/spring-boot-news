import React, {Component} from 'react';
import {Breadcrumb, Layout} from 'antd';
import {BrowserRouter, Route} from 'react-router-dom';
import SimpleHeader from './SimpleHeader';
import SimpleSiderNav from './SimpleSiderNav';

const {Content} = Layout;

export default class MainPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapsed: false,
      mode: 'inline',
      keyPath: [],

    };
  }

  onMenuOpenChange = (openKeys) => {
    //console.log(`onMenuOpenChange:`);
    //console.dirxml(openKeys);
  };

  onMenuItemSelect = (item) => {
    //console.log(`onMenuItemSelect:`);
    //console.dir(item);
  };
  onMenuClick = (item) => {
    this.setState({keyPath: item.keyPath.reverse()});
  };

  render() {
    const headerProps = {
      onOpenChange: this.onMenuOpenChange,
      onSelect: this.onMenuItemSelect,
    };

    const siderProps = {
        onClick: this.onMenuClick,
    };
    return (
        <BrowserRouter basename='/'>
          <Layout>
            <SimpleHeader headerProps={headerProps}/>
            <Layout>
              <SimpleSiderNav siderProps={siderProps}/>
              <Layout style={{padding: '0 24px 24px'}}>
                <Breadcrumb style={{margin: '12px 0'}}>
                  {this.state.keyPath.map(
                      key => (
                          <Breadcrumb.Item key={key}>{key}</Breadcrumb.Item>))}
                </Breadcrumb>
                <Route
                    path="/Mail2.2"
                    component={(match) => (<Content style={{
                      background: '#fff',
                      padding: 24,
                      margin: 0,
                      minHeight: 280,
                    }}>
                      Content
                    </Content>)}
                />
              </Layout>
            </Layout>
          </Layout>
        </BrowserRouter>);
  }
}

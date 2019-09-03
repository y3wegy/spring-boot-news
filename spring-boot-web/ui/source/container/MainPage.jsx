import React, {Component} from 'react';
import {Breadcrumb, Layout} from 'antd';
import SimpleHeader from '../component/menu/SimpleHeader';
import SimpleSiderNav from '../component/menu/SimpleSiderNav';
import DefaultContent from '../component/content/DefaultContent.jsx';

const {Content} = Layout;

export default class MainPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            collapsed: false,
            mode: 'inline',
            keyPath: []
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

        const siderNavProps = {
            onClick: this.onMenuClick,
        };
        return (
            <Layout>
                <SimpleHeader headerProps={headerProps}/>
                <Layout>
                    <SimpleSiderNav siderProps={siderNavProps}/>
                    <Layout style={{padding: '0 24px 24px'}}>
                        <Breadcrumb style={{margin: '12px 0'}}>
                            {this.state.keyPath.map(key => (
                                <Breadcrumb.Item key={key}>{key}</Breadcrumb.Item>))}
                        </Breadcrumb>
                        <Content style={{
                            background: '#fff',
                            padding: 24,
                            margin: 0,
                            minHeight: 280,
                        }}>
                            <DefaultContent/>
                        </Content>
                    </Layout>
                </Layout>
            </Layout>);
    }
}

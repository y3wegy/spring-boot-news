import React, {Component} from 'react';
import {Link, Route} from 'react-router-dom';

export default class SideBarExample extends Component {
    getRouteConfig = (match) => {
        return [
            {
                path: `${match.path}/`,
                exact: true,
                sidebar: () => <div>home!</div>,
                main: () => <h2>Home</h2>
            },
            {
                path: `${match.path}/bubblegum`,
                sidebar: () => <div>bubblegum!</div>,
                main: () => <h2>Bubblegum</h2>
            },
            {
                path: `${match.path}/shoelaces`,
                sidebar: () => <div>shoelaces!</div>,
                main: () => <h2>Shoelaces</h2>
            }
        ];
    }

    render() {
        const {match} = this.props;
        const actualConfig = this.getRouteConfig(match);
        return (
                <div style={{display: 'flex'}}>
                    <div style={{
                        padding: '10px',
                        width: '40%',
                        background: '#f0f0f0'
                    }}>
                        <ul style={{listStyleType: 'none', padding: 0}}>
                            <li><Link to={`${match.path}/`}>Home</Link></li>
                            <li><Link to={`${match.path}/bubblegum`}>Bubblegum</Link></li>
                            <li><Link to={`${match.path}/shoelaces`}>Shoelaces</Link></li>
                        </ul>

                        {actualConfig.map((route, index) => (
                            // You can render a <Route> in as many places
                            // as you want in your app. It will render along
                            // with any other <Route>s that also match the URL.
                            // So, a sidebar or breadcrumbs or anything else
                            // that requires you to render multiple things
                            // in multiple places at the same URL is nothing
                            // more than multiple <Route>s.
                            <Route
                                key={index}
                                path={route.path}
                                exact={route.exact}
                                component={route.sidebar}
                            />
                        ))}
                    </div>

                    <div style={{flex: 1, padding: '10px'}}>
                        {actualConfig.map((route, index) => (
                            // Render more <Route>s with the same paths as
                            // above, but different components this time.
                            <Route
                                key={index}
                                path={route.path}
                                exact={route.exact}
                                component={route.main}
                            />
                        ))}
                    </div>
                </div>
        );
    }

}
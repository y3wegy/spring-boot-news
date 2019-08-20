import React, {Component} from 'react';
import {Link, Redirect, Route} from 'react-router-dom';
import Login, {AuthButton, fakeAuth} from './Login.jsx';

export default class RedirectRouteExample extends Component {

  createPublicComponent = () => {
    return (<div id="public">Public</div>);
  };
  createProtectedComponent = () => {
    return (<div id="protected"><h3>Protected</h3></div>);
  };

  render() {
    const {match} = this.props;
    const PrivateRoute = ({component: Component, ...rest}) => (
        <Route {...rest} render={props => (
            fakeAuth.isAuthenticated ? (
                <Component {...props}/>
            ) : (
                <Redirect to={{
                  pathname: `${match.path}/login`,
                  state: {from: props.location},
                }}/>
            )
        )}/>
    );

    return (
        <div>
          <AuthButton/>
          <ul>
            <li><Link to={`${match.path}/public`}>Public Page</Link></li>
            <li><Link to={`${match.path}/protected`}>Protected Page</Link></li>
          </ul>
          <hr/>
          <Route path={`${match.path}/public`}
                 component={this.createPublicComponent}/>
          <Route path={`${match.path}/login`} component={Login}/>
          <PrivateRoute path={`${match.path}/protected`}
                        component={this.createProtectedComponent}/>
        </div>
    );
  }
}
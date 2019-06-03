import React, {Component} from 'react';
import {BrowserRouter, Link, Route, Switch} from 'react-router-dom';
import RouteExample from '../component/route/RouteExample';

export default class MixIndex extends Component {

    createMenu = () => {
        return (
            <BrowserRouter basename='/'>
                <div id='MainDiv'>
                    <ul>
                        <li>This line return</li>
                        <li><Link to='/routeExample'>React Route Example</Link></li>
                        <li><a href='/login.html'>App</a></li>
                    </ul>
                    <hr/>
                    <Switch>
                        <Route path='/routeExample' component={RouteExample}/>
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
    render = () => {
        return (<div id="main">
            {this.createMenu()}
        </div>);
    }
}
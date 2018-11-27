import React, {Component} from 'react';
import {Link, Route} from 'react-router-dom';

import Home from './Home.jsx';
import About from './About.jsx';
import Topics from './Topics.jsx';


export default class BasicRouteExample extends Component {

    render() {
        const {match} = this.props;
        return (
            <div id="BasicRouteExample">
                <ul>
                    <li><Link to={`${match.path}/`}>Home</Link></li>
                    <li><Link to={`${match.path}/about`}>About</Link></li>
                    <li><Link to={`${match.path}/topics`}>Topics</Link></li>
                </ul>

                <hr/>

                <Route exact path={`${match.path}/`} component={Home}/>
                <Route path={`${match.path}/about`} component={About}/>
                <Route path={`${match.path}/topics`} component={Topics}/>
            </div>
        );
    }
}
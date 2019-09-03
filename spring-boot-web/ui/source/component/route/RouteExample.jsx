import React, {Component} from 'react';
import {Link, Route, Switch} from 'react-router-dom';
import BasicRouteExample from './basic/BasicRouteExample';
import RedirectRouteExample from './redirect/RedirectRouteExample';
import PreventTransition from './preventTransition/PreventTransitionExample';
import SideBarExample from './sideBar/SideBarExample';
import AnimatedExample from './animate/AnimateExample';

export default class RouteExample extends Component {

    createRoute = (match) => {
        return (
            <div>
                <ul>
                    <li><Link to={`${match.path}/Basic`}>Basic Route</Link></li>
                    <li><Link to={`${match.path}/Protected`}>Redict Route</Link></li>
                    <li><Link to={`${match.path}/PreventTransition`}>Prevent
                        Transition</Link></li>
                    <li><Link to={`${match.path}/SideBar`}>SideBar</Link></li>
                    <li><Link to={`${match.path}/Animate`}>Animate</Link></li>
                </ul>
                <hr/>
                <Switch>
                    <Route path={`${match.path}/Basic`} component={BasicRouteExample}/>
                    <Route path={`${match.path}/Protected`}
                           component={RedirectRouteExample}/>
                    <Route path={`${match.path}/PreventTransition`}
                           component={PreventTransition}/>
                    <Route path={`${match.path}/SideBar`} component={SideBarExample}/>
                    <Route path={`${match.path}/Animate`} component={AnimatedExample}/>
                </Switch>
            </div>
        );
    };
    render = () => {
        const {match} = this.props;
        return (<div id="App">
            {this.createRoute(match)}
        </div>);
    };
}
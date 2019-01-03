import React, {Component} from 'react';
import {Link, Route} from 'react-router-dom';
import Form from './Form.jsx';

export default class PreventingTransitionsExample extends Component {
    render() {
        const {match} = this.props;
        return (
            <div>
                <ul>
                    <li><Link to={`${match.path}/`}>Form</Link></li>
                    <li><Link to={`${match.path}/one`}>One</Link></li>
                    <li><Link to={`${match.path}/two`}>Two</Link></li>
                </ul>
                <Route path={`${match.path}/`} exact component={Form}/>
                <Route path={`${match.path}/one`} render={() => <h3>One</h3>}/>
                <Route path={`${match.path}/two`} render={() => <h3>Two</h3>}/>
            </div>
        );
    }
}
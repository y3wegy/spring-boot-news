import React, {Component} from 'react';
import {Link, Route} from 'react-router-dom';
import Topic from './Topic.jsx';

export default class Topics extends Component {

  render = () => {
    const {match} = this.props;
    return (
        <div id="Topics">
          <h2>Topics</h2>
          <ul>
            <li>
              <Link to={`${match.path}/rendering`}>
                Rendering with React
              </Link>
            </li>
            <li>
              <Link to={`${match.path}/components`}>
                Components
              </Link>
            </li>
            <li>
              <Link to={`${match.path}/props-v-state`}>
                Props v. State
              </Link>
            </li>
          </ul>

          <Route path={`${match.path}/:topicId`} component={Topic}/>
          <Route exact path={match.path} render={() => (
              <h3>Please select a topic.</h3>
          )}/>
        </div>);
  };
}

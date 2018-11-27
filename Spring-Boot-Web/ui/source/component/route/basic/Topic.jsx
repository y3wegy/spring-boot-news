import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class Topic extends Component {
    static propTypes = {
        match: PropTypes.object.isRequired
    }

    render() {
        const {match} = this.props;
        return (
            <div id="Topic">
                <h3>{match.params.topicId}</h3>
            </div>);
    }
}
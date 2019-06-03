import React, {Component} from 'react'
import {Link} from 'react-router-dom';

const navItem = {
    textAlign: 'center',
    flex: 1,
    listStyleType: 'none',
    padding: '10px'
}
export default class NavLink extends Component {
    render() {
        return (
            <li style={navItem}>
                <Link {...this.props} style={{color: 'inherit'}}/>
            </li>
        );
    }
}
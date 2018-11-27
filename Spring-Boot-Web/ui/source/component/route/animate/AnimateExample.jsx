import React, {Component} from 'react'
import {CSSTransitionGroup} from 'react-transition-group'
import {Redirect, Route} from 'react-router-dom';
import NavLink from './NavLink.jsx';

export default class AnimateExample extends Component {

    createAnimate = () => {
        const {location, match} = this.props;

        const HSL = ({match: {params}}) => (
            <div style={{
                ...styles.fill,
                ...styles.hsl,
                background: `hsl(${params.h}, ${params.s}%, ${params.l}%)`
            }}>hsl({params.h}, {params.s}%, {params.l}%)</div>
        );

        return (<div style={styles.content}>
            <CSSTransitionGroup
                transitionName="fade"
                transitionEnterTimeout={300}
                transitionLeaveTimeout={300}
            >
                {/* no different than other usage of
                CSSTransitionGroup, just make
                sure to pass `location` to `Route`
                so it can match the old location
                as it animates out
            */}

                <Route exact path={`${match.path}/`} render={() => (
                    <Redirect to={`${match.path}/10/90/50`}/>
                )}/>
                <Route
                    location={location}
                    key={location.key}
                    path={`${match.path}/:h/:s/:l`}
                    component={HSL}
                />
            </CSSTransitionGroup>
        </div>);
    }

    render() {
        const {match} = this.props;
        return (
            <div>
                <ul style={styles.nav}>
                    <NavLink to={`${match.path}/10/90/50`}>Red</NavLink>
                    <NavLink to={`${match.path}/120/100/40`}>Green</NavLink>
                    <NavLink to={`${match.path}/200/100/40`}>Blue</NavLink>
                    <NavLink to={`${match.path}/310/100/50`}>Pink</NavLink>
                </ul>

                {this.createAnimate()}
            </div>
        );
    }
}

const styles = {};

styles.fill = {
    position: "absolute",
    left: 0,
    right: 0,
    top: 0,
    bottom: 0
};

styles.content = {
    ...styles.fill,
    top: "40px",
    textAlign: "center"
};

styles.nav = {
    padding: 0,
    margin: 0,
    position: "absolute",
    top: 0,
    height: "40px",
    width: "100%",
    display: "flex"
};

styles.navItem = {
    textAlign: "center",
    flex: 1,
    listStyleType: "none",
    padding: "10px"
};

styles.hsl = {
    ...styles.fill,
    color: "white",
    paddingTop: "20px",
    fontSize: "30px"
};

styles.rgb = {
    ...styles.fill,
    color: "white",
    paddingTop: "20px",
    fontSize: "30px"
};

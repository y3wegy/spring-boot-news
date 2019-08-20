import React, {Component} from 'react';
import {Prompt} from 'react-router-dom';

export default class Form extends Component {

  doSubmit = (event) => {
    event.preventDefault();
    event.target.reset();
    this.setState({
      isBlocking: false,
    });
  };

  constructor(props) {
    super(props);
    this.state = {isBlocking: false};
  }

  render() {
    const {isBlocking} = this.state;

    return (
        <form onSubmit={this.doSubmit}>
          <Prompt
              when={isBlocking}
              message={location => (
                  `Are you sure you want to go to ${location.pathname}`
              )}
          />

          <p>
            Blocking? {isBlocking ?
              'Yes, click a link or the back button' :
              'Nope'}
          </p>

          <p>
            <input
                size="50"
                placeholder="type something to block transitions"
                onChange={event => {
                  this.setState({
                    isBlocking: event.target.value.length > 0,
                  });
                }}
            />
          </p>

          <p>
            <button>Submit to stop blocking</button>
          </p>
        </form>
    );
  }

}
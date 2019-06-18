import * as React from "react";
import { Component } from 'react';
import { Route, Switch, BrowserRouter, Redirect } from "react-router-dom";
import Login from "./views/Login";
import SignUp from "./views/SignUp";
import UserHome from "./views/UserHome";
import { store } from './store'
import { setUser } from './actions'

const PrivateRoute = ({ ...rest }) => (
  <Route {...rest} render={(props) => (
    store.getState().isActive === true
      ? <UserHome />
      : <div><Redirect to='/' />{alert("Login Required")}</div>
  )} />
)

class App extends React.Component {
  // state = { :  }
  constructor(props) {
    super(props)
    const isActive = localStorage.getItem('isActive') === 'true'
    if (isActive == true) {
      const uname = localStorage.getItem('uname')
      const userId = localStorage.getItem('userId')
      let user = {
        userId,
        uname,
        isActive,
        isFree: false
      }
      store.dispatch(setUser(user))
    }

  }
  render() {
    // console.log(store.getState())
    return (
      <BrowserRouter>
        <Switch>
          <Route path="/" exact component={Login} />
          <Route path="/signup" component={SignUp} />
          <PrivateRoute path="/user" />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;

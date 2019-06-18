import * as React from "react";
import { Link } from "react-router-dom";
import { Message, Button, Grid, Divider, Segment, Icon, Header, Input } from 'semantic-ui-react'
import User from '../interfaces/UserIndo.interface'
import { store } from '../store'
import { setUser } from '../actions'



class Login extends React.Component {
  uname: string;
  pwd: string;

  render() {
    return (
      <div>
        <Header as='h2' icon textAlign="center">
          <Icon name="chess" />
          Home
        </Header>
        <Segment>
          <Grid columns={2} relaxed='very'>
            <Grid.Column textAlign="center">
              <Input placeholder="Username" onChange={e => this.uname = e.target.value} ></Input><br />
              <Input placeholder="Password" type="password" onChange={e => this.pwd = e.target.value} ></Input><br />
              <br />
              <Link to="/user"><Button icon labelPosition="left" onClick={() =>
                this.checkAccount(
                  this.uname,
                  this.pwd
                )
              }><Icon name="sign in" />Login</Button></Link>
            </Grid.Column>
            <Grid.Column textAlign="center" verticalAlign="middle">
              <Message >
                Don't have account? Create one here!
              </Message><br />
              <Link to="/signup"><Button icon labelPosition="left"><Icon name='signup' />Sign Up</Button></Link>
            </Grid.Column>
          </Grid>
          <Divider vertical>OR</Divider>
        </Segment>
      </div>
    );
  }


  checkAccount = (uname: string, pwd: string) => {
    console.log(uname)
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        let json = JSON.parse(this.responseText);
        console.log(json)
        let user = {
          userId: Number(json.userId),
          uname: json.uname,
          isActive: json.active,
          isFree: json.free
        }
        console.log(user)
        store.dispatch(setUser(user))

        const uname = store.getState().uname
        const isActive = store.getState().isActive
        const userId = store.getState().userId

        localStorage.setItem("userId", userId)
        localStorage.setItem("uname", uname);
        localStorage.setItem("isActive", isActive)

        if (json.isActive == false) alert("Credentials typed are wrong")
      }

    };
    xhttp.getAllResponseHeaders();
    xhttp.open("POST", "http://localhost:8888/login", false);
    xhttp.setRequestHeader("Content-type", "application/json");
    let data = JSON.stringify({ "username": uname, "password": pwd });
    console.log(data)
    xhttp.send(data);
  };

}



export default Login;

import * as React from "react";
import { Link } from "react-router-dom";
import { Message, Button, Grid, Divider, Segment, Icon, Header, Input } from 'semantic-ui-react'


class SignUp extends React.Component {
  // state = { :  }

  fname: string
  lname: string
  email: string
  uname: string
  pwd: string
  pwd2: string

  render() {
    return (
      <div>
        <Header as='h2' icon textAlign="center">
          <Icon name="chess" />
          SignUp
        </Header>
        <Segment>
          <Grid columns={2} relaxed='very'>
            <Grid.Column textAlign="center">
              <Input placeholder="First Name" onChange={e => this.fname = e.target.value} ></Input><br />
              <Input placeholder="Last Name" onChange={e => this.lname = e.target.value} ></Input><br />
              <Input placeholder="Email-ID" type="email" onChange={e => this.email = e.target.value} ></Input><br />
              <Input placeholder="UserName" onChange={e => this.uname = e.target.value} ></Input><br />
              <Input placeholder="Password" type="password" onChange={e => this.pwd = e.target.value} ></Input><br />
              <Input placeholder="Password" type="password" onChange={e => this.pwd2 = e.target.value} ></Input><br />
              <br />
              <Button id="submit" onClick={() => this.createAccount(this.fname,
                this.lname,
                this.email,
                this.uname,
                this.pwd,
                this.pwd2,
              )} >Sign Up</Button><br />
            </Grid.Column>
            <Grid.Column textAlign="center" verticalAlign="middle">
              <Message >
                Goto Login Page from Here!
      </Message><br />
              <Link to="/"><Button icon labelPosition="left"><Icon name="sign-in" />Login</Button></Link>
            </Grid.Column>
          </Grid>

          <Divider vertical>OR</Divider>
        </Segment>
      </div>
    );
  }

  createAccount = (fname: string, lname: string, email: string, uname: string, pwd: string, pwd2: string) => {

    if (pwd == pwd2) {
      let xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          let json = JSON.parse(this.responseText);
          console.log(json)
          alert(json.error);

        }
      };
      xhttp.getAllResponseHeaders();
      xhttp.open("POST", "http://localhost:8888/signup", false);
      xhttp.setRequestHeader("Content-type", "application/json");
      let data = JSON.stringify({ "fname": fname, "lname": lname, "emailId": email, "username": uname, "password": pwd });
      console.log(data)
      xhttp.send(data);
    }
    else { alert("Passwords Typed not matching") }
  }

}

export default SignUp;

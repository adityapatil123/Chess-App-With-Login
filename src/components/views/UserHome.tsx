import * as React from 'react';
import { Component } from 'react';
import { Sidebar, Menu, Icon, Image, Header, Segment, Button, MessageList, Message } from "semantic-ui-react"
import RemainUsers from './UserHomeComponents/RemainUsers'
import RequestBoard from './UserHomeComponents/RequestBoard'
import { store } from '../store'
import { setUser, unSetUser } from "../actions";



class UserHome extends React.Component {

    interval;
    signoutButtonClicked: boolean;
    userClicked;

    componentDidMount() {
        this.getUpdate(store.getState().uname)
        this.interval = setInterval(() => { this.getUpdate(store.getState().uname) }, 10000)      // 10 sec delay for refreshing remaining users state
    }

    componentWillUnmount() {
        clearInterval(this.interval)
        if (this.signoutButtonClicked != true) {
            const conf = confirm("Are you sure? You will be logged out")
            if (conf == true) {
                this.signOut(store.getState().uname);
            }
            else window.history.forward()
        }
        else this.signOut(store.getState().uname);
        // this.checkForBack(store.getState().uname)
    }


    render() {
        return (
            <div>
                <Header as='h2' icon textAlign="center">
                    <Icon name="chess" />
                    Welcome {store.getState().uname}!!
                </Header>



                <Sidebar.Pushable as={Segment}>
                    <Sidebar
                        as={Menu}
                        animation='push'
                        icon='labeled'
                        inverted
                        vertical
                        visible
                        width='thin'>
                        <RemainUsers handleClick={() => this.getUpdate(store.getState().uname)} />
                    </Sidebar>

                    <Sidebar.Pusher>
                        <Segment id="ChessPlace">
                            <RequestBoard handleClick={() => this.getUpdate(store.getState().uname)} />
                        </Segment>
                    </Sidebar.Pusher>

                </Sidebar.Pushable>

                <Button icon labelPosition="left" onClick={() => this.checkForSignout(store.getState().uname)} ><Icon name="sign-out" />Sign Out</Button>
            </div>
        );
    }

    checkForSignout = (uname: string) => {
        const conf = confirm("Are you sure?")
        if (conf == true) {
            // this.signOut(uname);
            this.signoutButtonClicked = true
            window.history.back();
        }
    };

    signOut = (uname: string) => {
        console.log(uname)
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText)
                console.log(json)

                localStorage.removeItem("userId");
                localStorage.removeItem("uname");
                localStorage.removeItem("isActive");
                localStorage.removeItem("clickedUser");
                localStorage.removeItem("clickedUserId");
            }
        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/signout", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "username": uname });
        console.log(data)
        xhttp.send(data);
        store.dispatch(unSetUser())
    }


    getUpdate = (uname: string) => {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText);
                console.log("here")
                let user = {
                    userId: json.userId,
                    uname: json.uname,
                    isActive: json.active,
                    isFree: json.free
                }
                // console.log(user)
                store.dispatch(setUser(user))
            }

        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/update", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "username": uname });
        // console.log(data)
        xhttp.send(data);
    };

};


export default UserHome;
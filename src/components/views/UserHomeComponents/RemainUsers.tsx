import * as React from 'react';
import { store } from '../../store'
import { Menu, Icon } from 'semantic-ui-react'

export interface RemainUsersProps {
    handleClick: any;
}

class RemainUsers extends React.Component<RemainUsersProps>{

    render() {
        return (
            <div>
                {this.getRemainUsers(store.getState().uname).map((user, index) =>
                    <Menu.Item key={index} as='a' onClick={() => {
                        localStorage.setItem("clickedUserId", user.userId)
                        localStorage.setItem("clickedUser", user.username)
                        this.props.handleClick()
                    }}>
                        {this.getColor(user)}
                        {user.username}
                    </Menu.Item>
                )}
            </div>
        );
    }

    getColor = (user) => {
        if (user.active == true) {
            if (user.free == true) {
                return <Icon name="users" color="green" size="small" />
            }
            return <Icon name="users" color="orange" size="small" />
        }
        else
            return <Icon name="users" color="red" size="small" />
    }

    getRemainUsers(uname: string) {
        let xhttp = new XMLHttpRequest();
        let remainUsers = []
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText);
                remainUsers = json.remainUsers
            }

        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/remainusers", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "username": uname });
        xhttp.send(data);

        return remainUsers
    }
}

export default RemainUsers;
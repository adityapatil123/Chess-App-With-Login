import * as React from 'react';
import { Header, MessageList, Message, Button, Image, Table } from 'semantic-ui-react'
import { store } from '../../store'
import ChessBoard from './ChessBoard'

// import {BlackPawnImg} from './images/BlackPawn'

export interface RequestBoardProps {
    handleClick: any;
}
class RequestBoard extends React.Component<RequestBoardProps> {
    isRequestSentByUser;
    isRequestSentByOther;
    isRequestMadeNow;
    isRequestAccepted;
    requestId;
    markup;


    render() {
        const userClickedId = localStorage.getItem("clickedUserId")
        const userClicked = localStorage.getItem("clickedUser")
        this.getRequestStatusForClicked(store.getState().userId, Number(userClickedId))

        console.log(this.isRequestAccepted)
        if (this.isRequestAccepted == true) {
            this.markup = <ChessBoard requestId={this.requestId} isThisSender={this.isRequestSentByUser} />
        }
        else if (this.isRequestSentByUser == true || this.isRequestMadeNow == true) {
            this.markup = <div><Message size="big"> You have made a request, wait for acceptance! </Message>
                <Message size="big">Request made is #{this.requestId}</Message>
            </div>
        }
        else if (this.isRequestSentByOther == true) {
            this.markup = <div>
                <Button onClick={() => {
                    this.makeChessBoard(this.requestId)
                    this.props.handleClick()
                }
                } primary>Accept Request</Button>
                <Message size="big">Accept request #{this.requestId} </Message>
                {/* <ChessBoard requestId={this.requestId} /> */}
            </div>
        }
        else {
            this.markup = <div>
                <Button primary onClick={() => {
                    this.sendRequest(store.getState().userId, Number(userClickedId))
                    this.props.handleClick()
                }
                }>Send Request</Button>
                <Message size="big">Make the request for playing</Message>
            </div>
        }


        if (userClickedId != null) {
            return (
                <div>
                    <Header color="violet" as='h1'>Username: {userClicked}</Header>
                    {this.markup}
                    {/* <ChessBoard requestId={this.requestId} /> */}
                </div>
            )
        }
        else {
            return (
                <div>
                    <Header color="violet" as='h1'>Are you ready to play game?</Header>
                    <Header as='h2'>Instructions for playing:</Header>
                    <MessageList>
                        <Message size="big">1) I hope, you know the rules of chess, just talking about this platform here</Message>
                        <Message size="big">2) On left side list of other users are there, click on Green one, it means he/she is online and free</Message>
                        <Message size="big">3) Send him/her game request, if he/she accepts it, game will begun</Message>
                        <Message size="big">4) Ready for fun,then All the Best for it</Message>


                        {/* {this.getChessBoard()} */}
                    </MessageList>
                </div>
            );
        }
    }

    sendRequest = (senderUserId: number, receiverUserId: number) => {
        console.log(senderUserId + receiverUserId)
        let xhttp = new XMLHttpRequest();

        let isRequestSentByUser = false;
        let isRequestSentByOther = false;
        let isRequestMadeNow = false;

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText);
                console.log(json)
                isRequestSentByUser = json.requestSentBySender
                isRequestSentByOther = json.requestSentByReceiver
                isRequestMadeNow = json.requestMadeNow
            }
        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/acceptrequest", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "senderUserId": senderUserId, "receiverUserId": receiverUserId });
        // console.log(data)
        xhttp.send(data);

        this.isRequestSentByUser = isRequestSentByUser
        this.isRequestSentByOther = isRequestSentByOther
        this.isRequestMadeNow = isRequestMadeNow
        this.isRequestAccepted = false
    }

    makeChessBoard = (requestId: number) => {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText);
                console.log(json)
            }

        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/makekeyboard", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "requestId": requestId });
        console.log(data)
        xhttp.send(data);
        this.isRequestAccepted = true
    }

    getRequestStatusForClicked = (senderUserId: number, receiverUserId: number) => {
        let xhttp = new XMLHttpRequest();

        let isRequestSentByUser = false;
        let isRequestSentByOther = false;
        let isRequestMadeNow = false;
        let isRequestAccepted = false;
        let requestId = 0;

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText);
                isRequestSentByUser = json.requestSentBySender
                isRequestSentByOther = json.requestSentByReceiver
                isRequestMadeNow = json.requestMadeNow
                isRequestAccepted = json.requestAccepted
                requestId = json.requestId
            }
        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/getrequestclicked", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "senderUserId": senderUserId, "receiverUserId": receiverUserId });
        // console.log(data)
        xhttp.send(data);

        this.isRequestSentByUser = isRequestSentByUser
        this.isRequestSentByOther = isRequestSentByOther
        this.isRequestMadeNow = isRequestMadeNow
        this.isRequestAccepted = isRequestAccepted
        this.requestId = requestId
    }

    getChessBoardColors = () => {
        let tabler = document.getElementById("myTable");
        console.log(tabler)
        let allRows = tabler.getElementsByClassName("tableRow");
        console.log(allRows)
        let clickedElement = null;
        let moveCount = 0;
        let canBeMoved = false;

        for (let i = 0; i < 8; i++) {
            for (let j = 0; j < 8; j++) {
                var cellsOfRow = allRows[i].getElementsByClassName("tableCell");
                // console.log(cellsOfRow[j])
                // cellsOfRow[j].setAttribute("onmouseover", "MouseOver(this)");
                // cellsOfRow[j].setAttribute("onmouseout", "MouseOut(this)");
                // cellsOfRow[j].setAttribute("onclick", "Clicking(this)");
                if ((i - j) % 2 == 0) {
                    cellsOfRow[j].setAttribute("className", cellsOfRow[j].className + " grey")
                    console.log(cellsOfRow[j])
                }
                if (cellsOfRow[j].classList.length == 1) {
                    cellsOfRow[j].setAttribute("id", "NUL" + i + j);
                }
                else {
                    cellsOfRow[j].setAttribute("id", cellsOfRow[j].id + i + j);
                }
            }
        }
    }

}



export default RequestBoard;

// const ChessBoard = () => {}

//     return (
//         <div>
//             <Header color="violet" as='h1'>Are you ready to play game?</Header>
//             <Header as='h2'>Instructions for playing:</Header>
//             <MessageList>
//                 <Message size="big">1) I hope, you know the rules of chess, just talking about this platform here</Message>
//                 <Message size="big">2) On left side list of other users are there, click on Green one, it means he/she is online and free</Message>
//                 <Message size="big">3) Send him/her game request, if he/she accepts it, game will begun</Message>
//                 <Message size="big">4) Ready for fun,then All the Best for it</Message>
//             </MessageList>
//         </div>
//     )
// }

// export default ChessBoard;
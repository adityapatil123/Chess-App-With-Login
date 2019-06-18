import * as React from 'react';
import './Classes.css'
import { element, number } from 'prop-types';

export interface ChessBoardProps {
    requestId: number
    isThisSender: boolean
}

class ChessBoard extends React.Component<ChessBoardProps> {
    clickedElement;
    count: 1;
    isWhiteChance;
    prevElemTypeID;
    chessBoard;
    matchId;
    interval;

    constructor(props) {
        super(props)
        // this.makeChessBoard(this.props.requestId)
        this.count = 1
        console.log("constructor")
        this.getChessBoard(this.props.requestId)
    }

    componentDidMount() {
        this.getFullIdwithXY()
        // this.props.handleClick()
        console.log("component mount")
        this.interval = setInterval(() => {
            this.getChessBoard(this.props.requestId)
            // this.getFullIdwithXY()
        }, 5000)      // 10 sec delay for refreshing remaining users state
    }

    componentWillUnmount() {
        clearInterval(this.interval)
    }

    componentDidUpdate() {
        this.getFullIdwithXY()
    }
    // style={{ backgroundColor: color }}
    render() {
        let demo1Markup;
        if (this.isWhiteChance == true) { demo1Markup = "Its White's Time" }
        else { demo1Markup = "Its Black's Time" }

        let getTableCellMarkup = (i: number, className: string, id: string) => {
            if (this.isWhiteChance == this.props.isThisSender) { return <div key={i} onClick={this.clicking} onMouseOut={this.mouseOut} onMouseOver={this.mouseOver} className={"tableCell " + className} id={id}></div> }
            else { return <div key={i} className={"tableCell " + className} id={id}></div> }
        }
        let getTableRowWhite = (i: number) => {
            return <div className="tableRow">{this.chessBoard[i].map((element, i) => getTableCellMarkup(i, element.slice(0, 2), element))} </div>
        }
        let getTableRowBlack = (i: number) => {
            return <div className="tableRow">{this.chessBoard[i].map((element, i) => getTableCellMarkup(i, element.slice(0, 2), element)).reverse()} </div>
        }

        if (this.props.isThisSender == true) {
            return (
                <div>
                    <h2>Match #{this.matchId}</h2>
                    <div id="myTable" className="tabler">
                        {getTableRowWhite(0)}
                        {getTableRowWhite(1)}
                        {getTableRowWhite(2)}
                        {getTableRowWhite(3)}
                        {getTableRowWhite(4)}
                        {getTableRowWhite(5)}
                        {getTableRowWhite(6)}
                        {getTableRowWhite(7)}
                    </div>
                    <br />
                    <div id="demo">Clicking Information</div>
                    <div id="demo1">{demo1Markup}</div>
                </div>
            );
        }
        else {
            return (
                <div>
                    <h2>Match #{this.matchId}</h2>
                    <div id="myTable" className="tabler">
                        {getTableRowBlack(7)}
                        {getTableRowBlack(6)}
                        {getTableRowBlack(5)}
                        {getTableRowBlack(4)}
                        {getTableRowBlack(3)}
                        {getTableRowBlack(2)}
                        {getTableRowBlack(1)}
                        {getTableRowBlack(0)}
                    </div>
                    <br />
                    <div id="demo">Clicking Information</div>
                    <div id="demo1">{demo1Markup}</div>
                </div>
            );
        }
    }

    getFullIdwithXY = () => {
        let isWhite = this.props.isThisSender
        let tabler = document.getElementById("myTable");
        // console.log(tabler)
        let allRows = tabler.getElementsByClassName("tableRow");
        console.log(allRows)
        for (let i = 0; i < 8; i++) {
            let cellsOfRow = allRows[i].getElementsByClassName("tableCell");
            for (let j = 0; j < 8; j++) {
                let i1 = 0
                let j1 = 0
                if (isWhite == true) {
                    i1 = i
                    j1 = j
                }
                else {
                    i1 = 7 - i
                    j1 = 7 - j
                }
                if (cellsOfRow[j].classList.length == 1) {
                    const id1 = "NUL" + i1 + j1
                    cellsOfRow[j].setAttribute("id", id1)
                }
                else {
                    if (cellsOfRow[j].id.length == 3) {
                        const id2 = cellsOfRow[j].id + i1 + j1
                        cellsOfRow[j].setAttribute("id", id2);
                    }
                }

                if ((i - j) % 2 == 0) {
                    cellsOfRow[j].setAttribute('style', 'background-color: grey');
                }
            }
        }
    }

    clicking = (e) => {
        let elem = e.target
        let elemTypeWithID = elem.id.slice(0, 3);
        let x = parseInt(elem.id[4]) + 1;
        let y = parseInt(elem.id[3]) + 1;
        console.log(x + "," + y)
        console.log(Number(this.count))

        if (this.count == 1) {
            if (elemTypeWithID != "NUL") {
                document.getElementById("demo").innerHTML = "Clicked is " + elemTypeWithID + " & position is x = " + x + " & y = " + y + " Element is selected!!!!";
                if (this.isWhiteChance == true) {
                    if (elemTypeWithID[0] == "W") {
                        elem.style.backgroundColor = "red";
                        this.clickedElement = elem;
                        this.prevElemTypeID = elemTypeWithID;
                        this.count++;
                    }
                }
                else {
                    if (elemTypeWithID[0] == "B") {
                        elem.style.backgroundColor = "red";
                        this.clickedElement = elem;
                        this.prevElemTypeID = elemTypeWithID;
                        this.count++;
                    }
                }

            }
            else {
                document.getElementById("demo").innerHTML = "Nothing is present here,Click some element & position is x = " + x + " & y = " + y;
            }

        }

        else {
            let xPrevElem = parseInt(this.clickedElement.id[4]);
            let yPrevElem = parseInt(this.clickedElement.id[3]);
            let tabler = document.getElementById("myTable");
            let allRows = tabler.getElementsByClassName("tableRow");
            console.log(elemTypeWithID)

            if ((elemTypeWithID == "NUL") || (elemTypeWithID[0] != this.clickedElement.id[0])) {
                this.count--;
                console.log(elemTypeWithID)
                document.getElementById("demo").innerHTML = "Clicked is " + this.prevElemTypeID + " & position is x = " + x + " & y = " + y + " Element is moved!!!!";
                // var backEnd_move = "false";                                        
                const xNew = x - 1;
                const yNew = y - 1;

                let check = null;
                let kingAlive = null;
                let isMoved = null;

                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var json = JSON.parse(this.responseText);
                        isMoved = json.moved;
                        check = json.thereCheck;
                        kingAlive = json.bothKingAlive;
                        document.getElementById("demo1").innerHTML = json.msg;
                        console.log(json);
                    }
                };
                xhttp.open("POST", "http://localhost:8888/movecoin", false)
                xhttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
                var moveData = JSON.stringify({ "requestId": this.props.requestId, "sourceId": this.clickedElement.id, "destinationId": elem.id });
                xhttp.send(moveData);

                if (isMoved == true) {
                    console.log(this.clickedElement.className)
                    elem.setAttribute("class", this.clickedElement.className)
                    console.log(this.prevElemTypeID + yNew + xNew)
                    elem.setAttribute("id", (this.prevElemTypeID + yNew + xNew));
                    this.clickedElement.setAttribute("class", "tableCell ");
                    this.clickedElement.setAttribute("id", "NUL" + yPrevElem + xPrevElem);
                    // this.moveCount++;
                    isMoved = false;

                    if (check == true) {
                        alert("Check Alert!!");
                    }
                }

                if (kingAlive == false) {
                    alert("Game is over!!Please refresh the page for new game!!")
                }


                if ((xPrevElem - yPrevElem) % 2 == 0) {
                    // this.clickedElement.setAttribute('style', 'background-color: grey');
                    this.clickedElement.style.backgroundColor = "grey";
                }
                else {
                    this.clickedElement.style.backgroundColor = "white";
                }
                this.clickedElement = null;

            }
            else {
                if ((xPrevElem - yPrevElem) % 2 == 0) {
                    this.clickedElement.style.backgroundColor = "grey";
                }
                else this.clickedElement.style.backgroundColor = "white";

                if (elem == this.clickedElement) {
                    this.clickedElement = null;
                    this.count--;
                }
                else {
                    document.getElementById("demo").innerHTML = "Clicked is " + elemTypeWithID + " & position is x = " + x + " & y = " + y + " Element is selected!!!!";
                    this.clickedElement = elem;
                    elem.style.backgroundColor = "red";
                    this.prevElemTypeID = elemTypeWithID;
                }
            }
        }

    }

    mouseOver = (e) => {
        let elem = e.target
        if (this.clickedElement != elem) {
            let elem = e.target
            elem.style.backgroundColor = 'yellow'
        }
    }

    mouseOut = (e) => {
        let elem = e.target
        if (this.clickedElement != elem) {
            var x = parseInt(elem.id[4]);
            var y = parseInt(elem.id[3]);
            if ((x - y) % 2 == 0) {
                elem.style.backgroundColor = "grey";
            }
            else elem.style.backgroundColor = "white";
        }
    }

    getChessBoard = (requestId: number) => {
        // this.getFullIdwithXY()
        let json: any
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                json = JSON.parse(this.responseText);
                console.log(json)
            }
        };
        xhttp.getAllResponseHeaders();
        xhttp.open("POST", "http://localhost:8888/getkeyboard", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        let data = JSON.stringify({ "requestId": requestId });
        console.log(data)
        xhttp.send(data);

        this.chessBoard = json.chessBoard
        this.matchId = json.matchId
        this.isWhiteChance = json.whiteChance
    }
}

export default ChessBoard;



/*


*/
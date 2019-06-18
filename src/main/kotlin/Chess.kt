package patil.aditya.login

import coinMap
import coinString
import destinationPlaceString
import getCoin
import getPlace
import killedCoinString
import printTable
import userInstr
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun makeChessBoard(request: ChessBoardRequest):MakeChessBoardResponse
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"
    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    var currentMatchId = 0
    val queryIfMatchGoingOn = "select MatchID from MatchHistory where RequestID=(?) and IsMatchActive=True;"
    val  st : PreparedStatement = con.prepareStatement(queryIfMatchGoingOn)
    st.setInt(1,request.requestId)
    val  rs =  st.executeQuery()
    while(rs.next()){
        currentMatchId = rs.getInt("MatchID")
    }

    if(currentMatchId != 0)
    {
        return MakeChessBoardResponse(currentMatchId)
    }
    else {
        val queryLastMatchId = "SELECT MAX(MatchID) FROM MatchHistory;"
        val st3: PreparedStatement = con.prepareStatement(queryLastMatchId)
        val rs3 = st3.executeQuery()
        var prevMatchId = 0
        while (rs3.next()) {
            prevMatchId = rs3.getInt("MAX(MatchID)")
        }

        val queryUpdateRequest = "update Request set IsRequestAccepted=True where RequestID=(?)"
        val st4 : PreparedStatement = con.prepareStatement(queryUpdateRequest)
        st4.setInt(1,request.requestId)
        st4.executeUpdate()

        val queryWhiteChessBoard =
            "insert into MatchHistory(RequestID, MatchID, IsMatchActive, MoveID, Color, P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2) values(?,?,true,1,'W',17,27,37,47,57,67,77,87,18,28,38,48,58,68,78,88)"
        val queryBlackChessBoard =
            "insert into MatchHistory(RequestID, MatchID, IsMatchActive, MoveID, Color, P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2) values(?,?,true,2,'B',12,22,32,42,52,62,72,82,11,21,31,41,51,61,71,81)"
        val st1: PreparedStatement = con.prepareStatement(queryWhiteChessBoard)
        val st2: PreparedStatement = con.prepareStatement(queryBlackChessBoard)

        //Checking if Sender has already sent the request
        st1.setInt(1, request.requestId)
        st1.setInt(2, prevMatchId + 1)
        st2.setInt(1, request.requestId)
        st2.setInt(2, prevMatchId + 1)

        st1.executeUpdate()
        st2.executeUpdate()
        st1.close()
        st2.close()

        con.close()

        val response = MakeChessBoardResponse(prevMatchId + 1)
        return response
    }
}


fun getChessBoard(request:ChessBoardRequest):GetChessBoardResponse
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"
    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val querySecondLastEntry = "SELECT * FROM MatchHistory where RequestID=(?) ORDER BY MoveID DESC LIMIT 1,1;"
    val queryLastEntry = "SELECT * FROM MatchHistory where RequestID=(?) ORDER BY MoveID DESC LIMIT 1;"
    val  st1 : PreparedStatement = con.prepareStatement(querySecondLastEntry)
    val  st2 : PreparedStatement = con.prepareStatement(queryLastEntry)

    st1.setInt(1,request.requestId)
    st2.setInt(1,request.requestId)

//    val blackCoinList:MutableList<Int> = ArrayList()
//    val whiteCoinList:MutableList<Int> = ArrayList()

    val coinList = mutableListOf<Pair<String,Array<Int>>>()
    var isWhiteChance = false

    val rs1 = st1.executeQuery()
    var matchId = 0
    var color1 = ""
    while(rs1.next()){
        matchId = rs1.getInt("MatchID")
        color1 = rs1.getString("Color")
        println(color1)
        if(color1 == "B")
        {
            isWhiteChance = false
            coinList.add(Pair("BR1", arrayOf(rs1.getInt("R1")/10,rs1.getInt("R1")%10)))
            coinList.add(Pair("BK1", arrayOf(rs1.getInt("K1")/10,rs1.getInt("K1")%10)))
            coinList.add(Pair("BB1", arrayOf(rs1.getInt("B1")/10,rs1.getInt("B1")%10)))
            coinList.add(Pair("BS1", arrayOf(rs1.getInt("S1")/10,rs1.getInt("S1")%10)))
            coinList.add(Pair("BQ1", arrayOf(rs1.getInt("Q1")/10,rs1.getInt("Q1")%10)))
            coinList.add(Pair("BB2", arrayOf(rs1.getInt("B2")/10,rs1.getInt("B2")%10)))
            coinList.add(Pair("BK2", arrayOf(rs1.getInt("K2")/10,rs1.getInt("K2")%10)))
            coinList.add(Pair("BR2", arrayOf(rs1.getInt("R2")/10,rs1.getInt("R2")%10)   ))
            coinList.add(Pair("BP1", arrayOf(rs1.getInt("P1")/10,rs1.getInt("P1")%10)))
            coinList.add(Pair("BP2", arrayOf(rs1.getInt("P2")/10,rs1.getInt("P2")%10)))
            coinList.add(Pair("BP3", arrayOf(rs1.getInt("P3")/10,rs1.getInt("P3")%10)))
            coinList.add(Pair("BP4", arrayOf(rs1.getInt("P4")/10,rs1.getInt("P4")%10)))
            coinList.add(Pair("BP5", arrayOf(rs1.getInt("P5")/10,rs1.getInt("P5")%10)))
            coinList.add(Pair("BP6", arrayOf(rs1.getInt("P6")/10,rs1.getInt("P6")%10)))
            coinList.add(Pair("BP7", arrayOf(rs1.getInt("P7")/10,rs1.getInt("P7")%10)))
            coinList.add(Pair("BP8", arrayOf(rs1.getInt("P8")/10,rs1.getInt("P8")%10)))
        }
        else
        {
            isWhiteChance = true
            coinList.add(Pair("WR1", arrayOf(rs1.getInt("R1")/10,rs1.getInt("R1")%10)))
            coinList.add(Pair("WK1", arrayOf(rs1.getInt("K1")/10,rs1.getInt("K1")%10)))
            coinList.add(Pair("WB1", arrayOf(rs1.getInt("B1")/10,rs1.getInt("B1")%10)))
            coinList.add(Pair("WS1", arrayOf(rs1.getInt("S1")/10,rs1.getInt("S1")%10)))
            coinList.add(Pair("WQ1", arrayOf(rs1.getInt("Q1")/10,rs1.getInt("Q1")%10)))
            coinList.add(Pair("WB2", arrayOf(rs1.getInt("B2")/10,rs1.getInt("B2")%10)))
            coinList.add(Pair("WK2", arrayOf(rs1.getInt("K2")/10,rs1.getInt("K2")%10)))
            coinList.add(Pair("WR2", arrayOf(rs1.getInt("R2")/10,rs1.getInt("R2")%10)))
            coinList.add(Pair("WP1", arrayOf(rs1.getInt("P1")/10,rs1.getInt("P1")%10)))
            coinList.add(Pair("WP2", arrayOf(rs1.getInt("P2")/10,rs1.getInt("P2")%10)))
            coinList.add(Pair("WP3", arrayOf(rs1.getInt("P3")/10,rs1.getInt("P3")%10)))
            coinList.add(Pair("WP4", arrayOf(rs1.getInt("P4")/10,rs1.getInt("P4")%10)))
            coinList.add(Pair("WP5", arrayOf(rs1.getInt("P5")/10,rs1.getInt("P5")%10)))
            coinList.add(Pair("WP6", arrayOf(rs1.getInt("P6")/10,rs1.getInt("P6")%10)))
            coinList.add(Pair("WP7", arrayOf(rs1.getInt("P7")/10,rs1.getInt("P7")%10)))
            coinList.add(Pair("WP8", arrayOf(rs1.getInt("P8")/10,rs1.getInt("P8")%10)))
        }
    }

    val rs2 = st2.executeQuery()
    var color2 = ""
    while(rs2.next()){
        color2 = rs2.getString("Color")
        if(color2 == "B")
        {
            coinList.add(Pair("BR1", arrayOf(rs2.getInt("R1")/10,rs2.getInt("R1")%10)))
            coinList.add(Pair("BK1", arrayOf(rs2.getInt("K1")/10,rs2.getInt("K1")%10)))
            coinList.add(Pair("BB1", arrayOf(rs2.getInt("B1")/10,rs2.getInt("B1")%10)))
            coinList.add(Pair("BS1", arrayOf(rs2.getInt("S1")/10,rs2.getInt("S1")%10)))
            coinList.add(Pair("BQ1", arrayOf(rs2.getInt("Q1")/10,rs2.getInt("Q1")%10)))
            coinList.add(Pair("BB2", arrayOf(rs2.getInt("B2")/10,rs2.getInt("B2")%10)))
            coinList.add(Pair("BK2", arrayOf(rs2.getInt("K2")/10,rs2.getInt("K2")%10)))
            coinList.add(Pair("BR2", arrayOf(rs2.getInt("R2")/10,rs2.getInt("R2")%10)))
            coinList.add(Pair("BP1", arrayOf(rs2.getInt("P1")/10,rs2.getInt("P1")%10)))
            coinList.add(Pair("BP2", arrayOf(rs2.getInt("P2")/10,rs2.getInt("P2")%10)))
            coinList.add(Pair("BP3", arrayOf(rs2.getInt("P3")/10,rs2.getInt("P3")%10)))
            coinList.add(Pair("BP4", arrayOf(rs2.getInt("P4")/10,rs2.getInt("P4")%10)))
            coinList.add(Pair("BP5", arrayOf(rs2.getInt("P5")/10,rs2.getInt("P5")%10)))
            coinList.add(Pair("BP6", arrayOf(rs2.getInt("P6")/10,rs2.getInt("P6")%10)))
            coinList.add(Pair("BP7", arrayOf(rs2.getInt("P7")/10,rs2.getInt("P7")%10)))
            coinList.add(Pair("BP8", arrayOf(rs2.getInt("P8")/10,rs2.getInt("P8")%10)))
        }
        else
        {
            coinList.add(Pair("WR1", arrayOf(rs2.getInt("R1")/10,rs2.getInt("R1")%10)))
            coinList.add(Pair("WK1", arrayOf(rs2.getInt("K1")/10,rs2.getInt("K1")%10)))
            coinList.add(Pair("WB1", arrayOf(rs2.getInt("B1")/10,rs2.getInt("B1")%10)))
            coinList.add(Pair("WS1", arrayOf(rs2.getInt("S1")/10,rs2.getInt("S1")%10)))
            coinList.add(Pair("WQ1", arrayOf(rs2.getInt("Q1")/10,rs2.getInt("Q1")%10)))
            coinList.add(Pair("WB2", arrayOf(rs2.getInt("B2")/10,rs2.getInt("B2")%10)))
            coinList.add(Pair("WK2", arrayOf(rs2.getInt("K2")/10,rs2.getInt("K2")%10)))
            coinList.add(Pair("WR2", arrayOf(rs2.getInt("R2")/10,rs2.getInt("R2")%10)))
            coinList.add(Pair("WP1", arrayOf(rs2.getInt("P1")/10,rs2.getInt("P1")%10)))
            coinList.add(Pair("WP2", arrayOf(rs2.getInt("P2")/10,rs2.getInt("P2")%10)))
            coinList.add(Pair("WP3", arrayOf(rs2.getInt("P3")/10,rs2.getInt("P3")%10)))
            coinList.add(Pair("WP4", arrayOf(rs2.getInt("P4")/10,rs2.getInt("P4")%10)))
            coinList.add(Pair("WP5", arrayOf(rs2.getInt("P5")/10,rs2.getInt("P5")%10)))
            coinList.add(Pair("WP6", arrayOf(rs2.getInt("P6")/10,rs2.getInt("P6")%10)))
            coinList.add(Pair("WP7", arrayOf(rs2.getInt("P7")/10,rs2.getInt("P7")%10)))
            coinList.add(Pair("WP8", arrayOf(rs2.getInt("P8")/10,rs2.getInt("P8")%10)))
        }
    }

    st1.close()
    st2.close()
    con.close()

    val chessBoard = placeAllElements(coinList)
    val response = GetChessBoardResponse(isWhiteChance, matchId, chessBoard)
    return response
}

fun placeAllElements(coinList: MutableList<Pair<String, Array<Int>>>):MutableList<MutableList<String>>
{
    coinMap.clear()
    coinList.forEach { pair ->
        val coin = getCoin(pair.first)
        val place = getPlace(pair.second)
        coinMap[place]=coin
    }
    printTable(coinMap)

    val chessBoard = mutableListOf<MutableList<String>>()

    for(i in 0..7){
        val row = mutableListOf<String>()
        for(j in 0..7){
            try {
                val place = getPlace(arrayOf(j+1,i+1))
//                chessBoard[i][j] = coinMap[place]!!.getID()
                row.add(j,coinMap[place]!!.getID())
            }
            catch(e:Exception){
                row.add(j,"")
            }
        }
        chessBoard.add(i,row)
    }

    return chessBoard
}

fun moveCoin(request: MoveRequest):MoveResponse
{
    val response =  userInstr(request.sourceId, request.destinationId)
    coinMap.clear()

    if(response.isMoved == true) {
        val url = "jdbc:mysql://localhost:3306/login"
        val uname = "root"
        val upwd = "root"
        Class.forName("com.mysql.cj.jdbc.Driver")
        val con: Connection = DriverManager.getConnection(url, uname, upwd)

        val querySecondLastEntry = "SELECT * FROM MatchHistory where RequestID=(?) ORDER BY MoveID DESC LIMIT 1,1;"
        val  st1 : PreparedStatement = con.prepareStatement(querySecondLastEntry)
        var moveId = 0
        var id = 0

        st1.setInt(1,request.requestId)
        val rs1 = st1.executeQuery()
        while(rs1.next()){
            id = rs1.getInt("ID")
            moveId = rs1.getInt("MoveID")
        }

        val queryCopySecondLast =  "insert into MatchHistory (RequestID,MatchID,IsMatchActive,MoveID,Color,P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2) SELECT RequestID,MatchID,IsMatchActive,MoveID,Color,P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2 FROM MatchHistory where ID=(?);"
        val  st2 : PreparedStatement = con.prepareStatement(queryCopySecondLast)
        st2.setInt(1,id)
        st2.executeUpdate()

        val queryUpdateSourceID = "update MatchHistory set MoveID=(?), ${coinString.slice(1..2)}=(?) where RequestID=(?) ORDER BY ID DESC LIMIT 1;"
        val st3 : PreparedStatement = con.prepareStatement(queryUpdateSourceID)
        val destinationPlaceInt = Integer.parseInt(destinationPlaceString)
        st3.setInt(1,moveId+2)
//        st3.setString(2,coinString.slice(1..2))
        st3.setInt(2,destinationPlaceInt)
        st3.setInt(3,request.requestId)
        st3.executeUpdate()

        if(killedCoinString != "NUL"){
            val queryLastEntry = "SELECT * FROM MatchHistory where RequestID=(?) ORDER BY MoveID DESC LIMIT 1;"
            val  st4 : PreparedStatement = con.prepareStatement(queryLastEntry)

            st4.setInt(1,request.requestId)
            val rs4 = st1.executeQuery()
            while(rs4.next()){
                id = rs4.getInt("ID")
                moveId = rs4.getInt("MoveID")
            }

            val queryCopyLast =  "insert into MatchHistory (RequestID,MatchID,IsMatchActive,MoveID,Color,P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2) SELECT RequestID,MatchID,IsMatchActive,MoveID,Color,P1, P2, P3, P4, P5, P6, P7, P8, R1, K1, B1, S1, Q1, B2, K2, R2 FROM MatchHistory where ID=(?);"
            val  st5 : PreparedStatement = con.prepareStatement(queryCopyLast)
            st5.setInt(1,id)
            st5.executeUpdate()

            val queryUpdateDestinationID = "update MatchHistory set MoveID=(?), ${killedCoinString.slice(1..2)}=(?) where RequestID=(?) ORDER BY ID DESC LIMIT 1;"
            val st6 : PreparedStatement = con.prepareStatement(queryUpdateDestinationID)

            st6.setInt(1,moveId+2   )
//            st6.setString(2,killedCoinString.slice(1..2))
            st6.setInt(2,destinationPlaceInt)
            st6.setInt(3,request.requestId)
            st6.executeUpdate()

            st4.close()
            st5.close()
            st6.close()
        }

        st1.close()
        st2.close()
        st3.close()
        con.close()

    }

    return response
}
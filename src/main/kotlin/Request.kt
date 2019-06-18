package patil.aditya.login

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun sendRequest(request: RequestRequest):RequestResponse
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)
    val queryIfRequestPresent = "select * from Request where (SenderID=(?) and ReceiverID=(?)) and (IsRequestActive = true)"
    val  st1 : PreparedStatement = con.prepareStatement(queryIfRequestPresent)

    //Checking if Sender has already sent the request
    st1.setInt(1,request.senderUserId)
    st1.setInt(2,request.receiverUserId)

    var isRequestBySender = false
    var isRequestByReceiver = false
    var isRequestMadeNow = false
    var requestId = 0
    val rs1 = st1.executeQuery()
    while(rs1.next())
    {
        isRequestBySender = true
        requestId = rs1.getInt("RequestID")
    }


    if(isRequestBySender == false) {

        //Checking if Receiver has sent the request
        st1.setInt(1, request.receiverUserId)
        st1.setInt(2, request.senderUserId)
        val rs2 = st1.executeQuery()
        while (rs2.next()) {
            isRequestByReceiver = true
            requestId = rs2.getInt("RequestID")
        }
    }


    //if there is no such a request, make one
    if((isRequestByReceiver==false)&&(isRequestBySender==false)) {
        val queryAddRequest =
            "insert into Request(SenderID,ReceiverID,IsRequestActive,IsRequestAccepted) values(?,?,true,false)"
        val st2: PreparedStatement = con.prepareStatement(queryAddRequest)
        st2.setInt(1, request.senderUserId)
        st2.setInt(2, request.receiverUserId)
        st2.executeUpdate()
        isRequestMadeNow = true
        st2.close()
    }

    st1.close()
    con.close()

    val response = RequestResponse(requestId,isRequestBySender,isRequestByReceiver,isRequestMadeNow,false)
    return response
}

fun getRequestStatusClicked(request: RequestRequest):RequestResponse
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)
    val queryIfRequestPresent = "select * from Request where (SenderID=(?) and ReceiverID=(?)) and (IsRequestActive = true)"
    val  st1 : PreparedStatement = con.prepareStatement(queryIfRequestPresent)

    //Checking if Sender has already sent the request
    st1.setInt(1,request.senderUserId)
    st1.setInt(2,request.receiverUserId)

    var isRequestBySender = false
    var isRequestByReceiver = false
    var isRequestMadeNow = false
    var isRequestAccepted = false
    var requestId = 0
    val rs1 = st1.executeQuery()
    while(rs1.next())
    {
        isRequestBySender = true
        requestId = rs1.getInt("RequestID")
        isRequestAccepted = rs1.getBoolean("IsRequestAccepted")
    }


    if(isRequestBySender == false) {

        //Checking if Receiver has sent the request
        st1.setInt(1, request.receiverUserId)
        st1.setInt(2, request.senderUserId)
        val rs2 = st1.executeQuery()
        while (rs2.next()) {
            isRequestByReceiver = true
            requestId = rs2.getInt("RequestID")
            isRequestAccepted = rs2.getBoolean("IsRequestAccepted")
        }
    }

    st1.close()
    con.close()

    val response = RequestResponse(requestId,isRequestBySender,isRequestByReceiver,isRequestMadeNow,isRequestAccepted)
    return response
}
package patil.aditya.login

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun addUser(request:SignUpRequest):Boolean
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    val queryAdd = "insert into Person(FirstName,LastName,EmailID,UserName,Password) values(?,?,?,?,?)"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val  st1 : PreparedStatement = con.prepareStatement(queryAdd)
    st1.setString(1, request.fname)
    st1.setString(2,request.lname)
    st1.setString(3,request.emailId)
    st1.setString(4,request.username)
    st1.setString(5,request.password)

    val rs = st1.executeUpdate()
    var isAccountCreated =false

    if(rs==1)
    {
        isAccountCreated = true
    }

    st1.close()
    con.close()

    return isAccountCreated
}
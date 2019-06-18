package patil.aditya.login

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun signIn(request: LoginRequest):User
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    var unameUser = ""
    var userId=0
    var isActive = false
    var isFree = false
    var msg = "Password is Wrong"

    val querySelect = "select * from Person where (UserName) = (?)"
    val queryMakeActive = "update Person set IsActive=true, IsFree=true where (UserName) = (?)"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val  st1 : PreparedStatement = con.prepareStatement(querySelect)
    val  st2 : PreparedStatement = con.prepareStatement(queryMakeActive)
    st1.setString(1, request.username)
    st2.setString(1, request.username)
    val rs1 = st1.executeQuery()

    var pwdUser = ""
    while(rs1.next())
    {
        pwdUser = rs1.getString("Password")
        if(pwdUser==request.password)
        {
            st2.executeUpdate()
            msg = "Password is Correct"
            unameUser = rs1.getString("UserName")
            userId = rs1.getInt("UserID")
            isActive = true
            isFree = true
        }
    }

    st1.close()
    st2.close()
    con.close()

    val user = User(userId,unameUser,msg,isActive,isFree)
    return user
}

fun logOut(request: UsernameRequest):Boolean
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    val isActive = false

    val queryMakeInactive = "update Person set IsActive=false, IsFree=false where (UserName) = (?)"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val  st1 : PreparedStatement = con.prepareStatement(queryMakeInactive)
    st1.setString(1, request.username)

    st1.executeUpdate()

    st1.close()
    con.close()

    return isActive
}
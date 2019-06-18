package patil.aditya.login

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun update(request: UsernameRequest): User{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    var unameUser = ""
    var isActive = false
    var isFree = false
    var userId = 0
    var msg = "Password is Wrong"

    val querySelect = "select * from Person where (UserName) = (?)"
    val querySelectRemainUsers = "select * from Person where not (UserName) = (?)"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val  st1 : PreparedStatement = con.prepareStatement(querySelect)
    st1.setString(1, request.username)
    val rs1 = st1.executeQuery()

    var userRemain = mutableListOf<UserRemain>()
    while(rs1.next())
    {
        msg = "Password is Correct"
        unameUser = rs1.getString("UserName")
        userId = rs1.getInt("UserID")
        isActive = true
        isFree = true
    }

    st1.close()
    con.close()

    val user = User(userId,unameUser,msg,isActive,isFree)
    return user
}


fun getRemainUsers(request: UsernameRequest):RemainUsers
{
    val url = "jdbc:mysql://localhost:3306/login"
    val uname = "root"
    val upwd = "root"

    var unameUser = ""
    var isActive = false
    var isFree = false
    var msg = "Password is Wrong"

    val querySelectRemainUsers = "select * from Person where not (UserName) = (?)"

    Class.forName("com.mysql.cj.jdbc.Driver")
    val con : Connection = DriverManager.getConnection(url,uname,upwd)

    val  st1 : PreparedStatement = con.prepareStatement(querySelectRemainUsers)

    st1.setString(1, request.username)

    val rs1 = st1.executeQuery()

    var pwdUser = ""
    val userRemain = mutableListOf<UserRemain>()

    var userIdRemain = 0
    var unameRemain = ""
    var isActiveRemain = false
    var isFreeRemain = false

    while(rs1.next())
    {
        userIdRemain = rs1.getInt("UserID")
        unameRemain =  rs1.getString("UserName")
        isActiveRemain =  rs1.getBoolean("IsActive")
        isFreeRemain =  rs1.getBoolean("IsFree")
        userRemain.add(UserRemain(userIdRemain,unameRemain,isActiveRemain,isFreeRemain))
    }

    st1.close()
    con.close()

    val users = RemainUsers(userRemain)
    return users
}
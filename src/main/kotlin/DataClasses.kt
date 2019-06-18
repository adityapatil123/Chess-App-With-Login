package patil.aditya.login

data class LoginRequest(val username: String="", val password: String="")

data class UsernameRequest(val username: String="")

data class Response(val success: Boolean, val username: String, val msg:String)

data class User(val userId:Int, val uname:String, val msg:String, val isActive:Boolean,val isFree:Boolean)

data class UserRemain(val userId: Int, val username: String,val isActive: Boolean,val isFree:Boolean)

data class SignUpRequest(val fname:String, val lname:String, val emailId:String, val username:String, val password: String)

data class RequestRequest(val senderUserId:Int, val receiverUserId:Int)
data class RequestResponse(val requestId:Int, val isRequestSentBySender: Boolean,val isRequestSentByReceiver: Boolean,val isRequestMadeNow: Boolean, val isRequestAccepted: Boolean)

data class ChessBoardRequest(val requestId: Int)

data class MakeChessBoardResponse(val matchID:Int)
data class GetChessBoardResponse(val isWhiteChance:Boolean, val matchId: Int, val chessBoard:MutableList<MutableList<String>>)

data class MoveRequest(val requestId: Int, val sourceId:String, val destinationId:String)
data class MoveResponse(val isMoved:Boolean, val isThereCheck:Boolean, val bothKingAlive:Boolean)

data class RemainUsers(val remainUsers:List<UserRemain>)

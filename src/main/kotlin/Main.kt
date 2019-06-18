package patil.aditya.login


import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import org.slf4j.LoggerFactory
import java.security.Security

val log = LoggerFactory.getLogger("server")
val APP_ENDPOINT = "sampleserver"
val USER_TOKEN_KEY = "userKey"

fun main(args: Array<String>) {
    log.debug("Starting application")
//    Security.addProvider(BouncyCastleProvider())
    Security.setProperty("crypto.policy", "unlimited")
    embeddedServer(Jetty, host="localhost", port = 8888,
        module = Application::main).start(wait = true)
}

fun Application.main() {
    install(io.ktor.features.ContentNegotiation) {
        jackson {
            dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy")
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(DefaultHeaders)
    install(CORS) {
        //maxAge = Duration.ofDays(1)
        anyHost()
        exposeHeader(USER_TOKEN_KEY)
        header(USER_TOKEN_KEY)
    }
    routing {
        route("/") {

            get("/") {
                call.respondText("Hello", ContentType.Text.Plain)
            }

            post("/login")                          //SignInOut -> signIn
            {
                call.receive<LoginRequest>().let { request->
                    call.respond(HttpStatusCode.OK, signIn(request))

                }
            }

            post("/signout")                          //SignInOut -> signOut
            {
                call.receive<UsernameRequest>().let { request->
                    call.respond(HttpStatusCode.OK, Response(logOut(request), request.username, "Logout"))
                }
            }

            post("/update")                          //Update
            {
                call.receive<UsernameRequest>().let { request->
                    call.respond(HttpStatusCode.OK, update(request))

                }
            }

            post("/signup")                          //SignUp
            {
                call.receive<SignUpRequest>().let { request->
                    if(addUser(request))
                    {
                        call.respond(HttpStatusCode.OK, Response(true, request.username, "Account Created for ${request.username}, Go to Login for Page for Login!"))
                    }
                    call.respond(HttpStatusCode.OK, Response(false, request.username, "Username/EmailID already exists!"))

                }
            }

            post("/sendrequest")                          //Request
            {
                call.receive<RequestRequest>().let { request->
                    call.respond(HttpStatusCode.OK, sendRequest(request))

                }
            }

            post("/getrequestclicked")                          //RequestClicked
            {
                call.receive<RequestRequest>().let { request->
                    call.respond(HttpStatusCode.OK, getRequestStatusClicked(    request))

                }
            }

            post("/remainusers")                          //RemainUsers
            {
                call.receive<UsernameRequest>().let { request->
                    call.respond(HttpStatusCode.OK, getRemainUsers(request))

                }
            }

            post("/makekeyboard")                          //Initial Keyboard
            {
                call.receive<ChessBoardRequest>().let { request->
                    call.respond(HttpStatusCode.OK, makeChessBoard(request))

                }
            }

            post("/getkeyboard")                          //Get Keyboard
            {
                call.receive<ChessBoardRequest>().let { request->
                    call.respond(HttpStatusCode.OK, getChessBoard(request))

                }
            }

            post("/movecoin")                          //Move Coin
            {
                call.receive<MoveRequest>().let { request->
                    call.respond(HttpStatusCode.OK, moveCoin(request))
                }
            }

        }
    }
}
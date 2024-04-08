package jinvicky.com.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jinvicky.com.service.MemberService
import org.koin.ktor.ext.inject

fun Route.memberRoutes() {
    val memberService: MemberService by inject()

    route("/member") {
        get("/") {
            call.respondText("member list")
        }

        get("/check/{id}") {
            val id = call.parameters["id"]?.toInt() ?: 0
            call.respond(memberService.selectDetail(id) != null)
        }
    }

}
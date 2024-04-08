package jinvicky.com.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jinvicky.com.routes.bannerRoutes
import jinvicky.com.routes.memberRoutes
import jinvicky.com.routes.testRoutes

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        bannerRoutes()
        memberRoutes()
        testRoutes()
    }
}

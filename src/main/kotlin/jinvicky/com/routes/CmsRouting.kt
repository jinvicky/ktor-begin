package jinvicky.com.routes

import io.ktor.server.routing.*

fun Route.commissionRoutes() {
    route("/banner") {
        get("/list") {
            println("1. 배너 리스트 조회")
        }

    }
}



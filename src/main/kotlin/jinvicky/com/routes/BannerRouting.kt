package jinvicky.com.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jinvicky.com.model.ExposedBanner
import jinvicky.com.service.BannerService
import org.koin.ktor.ext.inject

fun Route.bannerRoutes() {
    val bannerService: BannerService by inject()

    route("/banner") {
        get("/") {
            bannerService.bannerList().let {
                call.respond(it)
            }
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: 0
            println("banner.... $id")
            bannerService.bannerDetail(id).let {
                call.respond(it ?: "Not Found")
            }
        }

        post {
            val banner = call.receive<ExposedBanner>() // requestBody로 postman 테스트, ok
            println("banner.... $banner")
            bannerService.bannerInsert(banner).let {
                call.respond(it)
            }
        }
    }
}



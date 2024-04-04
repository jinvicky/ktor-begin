package jinvicky.com.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jinvicky.com.service.BannerService
import org.koin.core.qualifier.named
import org.koin.ktor.ext.inject

fun Route.bannerRoutes() {
    val bannerService: BannerService by inject()
    val bannerService2: BannerService by inject(named("bannerService2"))

    route("/banner") {
        get("/list") {
            bannerService.bannerList().let {
                call.respond(it)
            }
        }

        get("/list2") {
            bannerService2.bannerList().let {
                call.respond(it)
            }
        }
    }
}



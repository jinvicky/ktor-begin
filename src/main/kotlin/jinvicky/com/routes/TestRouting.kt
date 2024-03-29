package jinvicky.com.routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jinvicky.com.model.vo.Banner
import java.io.File

fun Route.testRoutes() {
    // 참고 url
    // https://ktor.io/docs/requests.html#objects

    route ("/test") {
        /**
         * request 정보 수신
         */
        get("/request") {
            println("1. ${call.request.queryParameters}")
            println("2. ${call.request.queryParameters["name"]}")
            println("3. ${call.request.uri}")
            println("4. ${call.parameters["email"]}")
            call.respondText("Test")
        }

        post("/banner") {
            val banner = call.receive<Banner>()
            println("1. 배너 객체:  $banner")
        }

        post("/pure/text") {
            val text = call.receiveText()
            println("1. 텍스트: $text")
        }

        post("/form") {
            val form = call.receiveParameters()
            println("1. 폼: $form")
            val username = form["username"]
            call.respondText("User is : $username")
        }

        post("/upload") {
            val multipartData = call.receiveMultipart()
            var fileDescription = ""
            var fileName = ""
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                    }

                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("uploads/$fileName").writeBytes(fileBytes)
                    }

                    else -> {}
                }
                part.dispose()
            }
            call.respondText("Upload")
        }
    }
}
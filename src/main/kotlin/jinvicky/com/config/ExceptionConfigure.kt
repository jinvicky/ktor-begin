package jinvicky.com.config

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory


fun Application.configureException() {

    install(StatusPages) {
        val logger = LoggerFactory.getLogger(Application::class.java)

        /** rest api 의도적 400 전송할 경우 응답 code **/
        exception<BadRequestException> {
                call, cause -> logger.error(cause.stackTraceToString())
            call.respond(HttpStatusCode.BadRequest, "이것은 잘못된 요청입니다.")
        }

        exception<NotFoundException> {
                call, cause -> logger.error(cause.stackTraceToString())
            call.respond(HttpStatusCode.NotFound, "이것은 찾을 수 없습니다. 404")
        }

        exception<Throwable> {
                call, cause -> logger.error(cause.stackTraceToString())
            call.respond(HttpStatusCode.InternalServerError, "이것은 서버 에러입니다. 500")
        }

    }

}
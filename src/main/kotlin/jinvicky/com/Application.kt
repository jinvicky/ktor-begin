package jinvicky.com

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import jinvicky.com.config.configureRouting
import jinvicky.com.config.configureSerialization
import jinvicky.com.logger.configureMonitoring
fun main() {
    embeddedServer(Netty, port = 8085, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()

    /**
     * 신규 추가
     */
    configureRouting() // 모든 라우팅 처리

}


/**
 * configure 어렵...
 */
fun Application.configure() {
    install(ContentNegotiation) {
        json()
    }

//    install(CORS) {
//        anyHost()
//        allowHeader(HttpHeaders.ContentType)
//    }
}

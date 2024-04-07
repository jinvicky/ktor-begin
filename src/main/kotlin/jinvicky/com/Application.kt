package jinvicky.com

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import jinvicky.com.config.*

import org.koin.ktor.plugin.Koin
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

import jinvicky.com.logger.configureMonitoring
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() {
    startKoin {
        printLogger(Level.DEBUG)
        modules(appModule)
    }

    DatabaseConfigure.init()
    embeddedServer(Netty,
        port = 8085,
        host = "0.0.0.0",
        module = Application::module,
//        configure = { Application.configure}
    ).start(wait = true)
}

fun Application.module() {
    configureMonitoring() // 로깅
    configureSerialization()

    configureRouting() // 라우팅
    configureException() // 예외처리
}

fun Application.configure() {
    install(ContentNegotiation) {
        json()

    }
}


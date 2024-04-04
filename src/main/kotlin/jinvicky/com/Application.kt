package jinvicky.com

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

import io.ktor.server.plugins.contentnegotiation.*

import org.koin.ktor.plugin.Koin
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

import jinvicky.com.config.DatabaseConfigure
import jinvicky.com.config.appModule
import jinvicky.com.config.configureRouting
import jinvicky.com.config.configureSerialization
import jinvicky.com.logger.configureMonitoring


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
        configure = { Application::configure  }
    ).start(wait = true)

}

fun Application.module() {
    configureMonitoring()
    configureSerialization()

    configureRouting()
}

fun Application.configure() {
    install(ContentNegotiation) {
        json()
    }
}

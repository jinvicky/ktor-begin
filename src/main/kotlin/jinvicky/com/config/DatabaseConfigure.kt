package jinvicky.com.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseConfigure {
    private val config: Config = ConfigFactory.load("application.conf")
    private val jdbcURL: String = config.getString("datasource.url")
    private val driverClassName : String = config.getString("datasource.driver")
    private val dbUser: String = config.getString("datasource.user")
    private val dbPassword: String = config.getString("datasource.password")

    private fun createHikariDataSource(
        url: String,
        driver: String,
        user: String = dbUser,
        pwd: String = dbPassword
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        username = user
        password = pwd
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })

    fun init () {
        Database.connect(createHikariDataSource(
            url = jdbcURL,
            driver = driverClassName
        ))
    }
}


suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }


package jinvicky.com

import com.typesafe.config.ConfigFactory
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.*

class ApplicationTest {

    object Banners : Table("banner") {
        val id = integer("ID").autoIncrement()
        val img_url = varchar("img_url", 255)
    }
    @Test
    fun testRoot() = testApplication {

        val config = ConfigFactory.load("application.conf")
        val dbUrl = config.getString("datasource.url")
        val dbDriver = config.getString("datasource.driver")
        val dbUser = config.getString("datasource.user")
        val dbPassword = config.getString("datasource.password")

        Database.connect(
            url = dbUrl,
            driver = dbDriver,
            user = dbUser,
            password = dbPassword
        )

        transaction {
            val result = Banners.selectAll().toList()
            println(result)
            /**
             * suspend 키워드는 coroutine 바디 안에서만 사용할 수 있다.
             */
//           selectAll()
        }
    }
}

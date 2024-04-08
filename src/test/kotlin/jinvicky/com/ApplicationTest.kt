package jinvicky.com

import com.typesafe.config.ConfigFactory
import io.ktor.server.testing.*
import jinvicky.com.config.dbQuery
import jinvicky.com.model.Members
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.*

class ApplicationTest {

    @BeforeTest
    fun setup() {
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
    }
    @Test
    fun testMemberExists() = testApplication {
        transaction {
            val memberId = 29
            val result = Members.select { Members.id eq memberId }.singleOrNull()
            assert(result != null)
        }
    }
}

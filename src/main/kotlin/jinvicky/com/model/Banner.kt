package jinvicky.com.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Banners: Table("banner") {

    val id = integer("ID").autoIncrement()
    val imgUrl = varchar("IMG_URL", 255)
    val comment = varchar("CMMNT", 255)
    val hrefUrl = varchar("HREF_URL", 255)
    val delYn = varchar("DEL_YN", 1).default("N")
    val regDate = datetime("RGTR_DT").index().default(LocalDateTime.now())
}
class Banner(
    val id: Int,
    val imgUrl: String,
    val comment: String,
    val hrefUrl: String,
    val delYn: String?,
) {

}

@Serializable
data class ExposedBanner(
    val id: Int,
    val imgUrl: String,
    val comment: String,
    val hrefUrl: String,
    val delYn: String? = null
) {
    val safeDelYn: String
        get() = delYn ?: "N"
}
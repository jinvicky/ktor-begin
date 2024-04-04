package jinvicky.com.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Banners: Table("banner") {
    val id = integer("ID").autoIncrement()
    val img_url = varchar("img_url", 255)
}

class Banner(val id: Int, val img_url: String) {
    override fun toString(): String {
        return "Banner(id=$id, img_url='$img_url')"
    }
}

@Serializable
data class ExposedBanner(val id: Int, val img_url: String)
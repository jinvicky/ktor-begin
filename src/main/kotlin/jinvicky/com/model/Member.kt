package jinvicky.com.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


object Members: Table("member") {
    val id = integer("ID").autoIncrement()
}


@Serializable
data class ExposedMember(
    val id: Int,
) {
   companion object {
        fun fromRow(row: ResultRow) = ExposedMember(
            id = row[Members.id],
        )
    }
}
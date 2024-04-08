package jinvicky.com.extension

import jinvicky.com.model.ExposedMember
import jinvicky.com.model.Members
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.toExposedMember() = ExposedMember(
    this[Members.id]
)
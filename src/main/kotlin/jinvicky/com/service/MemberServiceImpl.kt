package jinvicky.com.service

import jinvicky.com.config.dbQuery
import jinvicky.com.extension.toExposedMember
import jinvicky.com.model.ExposedMember
import jinvicky.com.model.Members
import org.jetbrains.exposed.sql.select

class MemberServiceImpl: MemberService {
    override suspend fun selectDetail(id: Int): ExposedMember? {
        return dbQuery {
            Members.select { Members.id eq id }
                .map { it.toExposedMember() }
                .singleOrNull()
        }
    }
}
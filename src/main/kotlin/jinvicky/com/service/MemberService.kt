package jinvicky.com.service

import jinvicky.com.model.ExposedMember


interface MemberService {

    suspend fun selectDetail(id: Int): ExposedMember?
}
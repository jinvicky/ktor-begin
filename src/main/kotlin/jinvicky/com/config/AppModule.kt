package jinvicky.com.config

import jinvicky.com.service.BannerService
import jinvicky.com.service.BannerServiceImpl
import jinvicky.com.service.MemberService
import jinvicky.com.service.MemberServiceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<BannerService> { BannerServiceImpl() }
    single<MemberService> { MemberServiceImpl() }
}

package jinvicky.com.config

import jinvicky.com.service.BannerService
import jinvicky.com.service.BannerServiceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<BannerService> { BannerServiceImpl() }
//    single<BannerService>(named("bannerService2")) { BannerServiceImpl2() }
}

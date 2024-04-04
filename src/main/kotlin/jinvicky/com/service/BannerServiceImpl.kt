package jinvicky.com.service

import jinvicky.com.config.dbQuery
import jinvicky.com.model.Banners
import jinvicky.com.model.ExposedBanner
import org.jetbrains.exposed.sql.selectAll



class BannerServiceImpl: BannerService {

    override suspend fun bannerList(): List<ExposedBanner> {
        println("lets ktor! . BannerServiceImpl1")
        return dbQuery {
            Banners.selectAll().map { ExposedBanner(it[Banners.id], it[Banners.img_url]) }
        };
    }
}
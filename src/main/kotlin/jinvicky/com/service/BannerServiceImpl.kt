package jinvicky.com.service

import jinvicky.com.config.dbQuery
import jinvicky.com.extension.toExposedBanner
import jinvicky.com.model.Banners
import jinvicky.com.model.Banners.id
import jinvicky.com.model.ExposedBanner
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll



class BannerServiceImpl: BannerService {

    override suspend fun bannerList(): List<ExposedBanner> {
        println("lets ktor! . BannerList")
        return dbQuery {
            Banners.selectAll().map {
                it.toExposedBanner()
            }
        };
    }

    override suspend fun bannerDetail(id: Int): ExposedBanner? {
        println("lets ktor! . BannerDetail")
        return dbQuery {
            Banners.select { Banners.id eq id }
                .map { it.toExposedBanner() }
                .singleOrNull()
        }
    }


    override suspend fun bannerInsert(banner: ExposedBanner): Int {
        return dbQuery {
            Banners.insert {
                it[id] = banner.id
                it[imgUrl] = banner.imgUrl
                it[comment] = banner.comment
                it[hrefUrl] = banner.hrefUrl
                it[delYn] = banner.safeDelYn
            }[id]
        }
    }
}
package jinvicky.com.service

import jinvicky.com.model.ExposedBanner


interface BannerService {

    suspend fun bannerList(): List<ExposedBanner>

    suspend fun bannerDetail(id: Int): ExposedBanner?

    suspend fun bannerInsert(banner: ExposedBanner): Int
}
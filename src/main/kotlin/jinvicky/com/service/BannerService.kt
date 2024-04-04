package jinvicky.com.service

import jinvicky.com.model.ExposedBanner


interface BannerService {

    suspend fun bannerList(): List<ExposedBanner>
}
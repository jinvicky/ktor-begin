package jinvicky.com.extension

import jinvicky.com.model.Banners
import jinvicky.com.model.ExposedBanner
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toExposedBanner() = ExposedBanner(
    this[Banners.id],
    this[Banners.imgUrl],
    this[Banners.comment],
    this[Banners.hrefUrl],
    this[Banners.delYn]
)

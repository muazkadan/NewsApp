package net.mouazkaadan.inshort.data.model

import net.mouazkaadan.inshort.data.local.entity.NewsItemEntity

data class NewsResponseModel(
    val category: String,
    val `data`: List<NewsItem>,
    val error: String? = null,
    val success: Boolean
)

data class NewsItem(
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String?,
    val time: String,
    val title: String,
    val url: String
) {
    fun toNewsItemEntity(category: String): NewsItemEntity = NewsItemEntity(
        author = author,
        content = content,
        date = date,
        imageUrl = imageUrl,
        readMoreUrl = readMoreUrl.orEmpty(),
        time = time,
        title = title,
        url = url,
        category = category
    )
}

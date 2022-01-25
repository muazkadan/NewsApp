package net.mouazkaadan.inshort.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.mouazkaadan.inshort.data.model.NewsItem

@Entity
data class NewsItemEntity(
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String?,
    val time: String,
    val title: String,
    val url: String,
    val category: String,
    @PrimaryKey val id: Int? = null
) {
    fun toNewsItem(): NewsItem = NewsItem(
        author = author,
        content = content,
        date = date,
        imageUrl = imageUrl,
        readMoreUrl = readMoreUrl.orEmpty(),
        time = time,
        title = title,
        url = url
    )
}

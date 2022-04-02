package net.mouazkaadan.inshort.domain.mapper

import net.mouazkaadan.inshort.data.local.entity.NewsItemEntity
import net.mouazkaadan.inshort.data.model.NewsItem
import javax.inject.Inject

/**
 * @author muaz
 * Created on 4/2/2022.
 */
class NewsItemMapper @Inject constructor() {

    fun map(input: NewsItemEntity): NewsItem {
        return NewsItem(
            author = input.author,
            content = input.content,
            date = input.date,
            imageUrl = input.imageUrl,
            readMoreUrl = input.readMoreUrl.orEmpty(),
            time = input.time,
            title = input.title,
            url = input.url
        )
    }

    fun map(input: NewsItem, category: String): NewsItemEntity {
        return NewsItemEntity(
            author = input.author,
            content = input.content,
            date = input.date,
            imageUrl = input.imageUrl,
            readMoreUrl = input.readMoreUrl.orEmpty(),
            time = input.time,
            title = input.title,
            url = input.url,
            category = category
        )
    }
}

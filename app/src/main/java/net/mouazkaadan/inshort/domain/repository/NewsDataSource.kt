package net.mouazkaadan.inshort.domain.repository

import kotlinx.coroutines.flow.Flow
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.utils.Resource

interface NewsDataSource {

    fun getNews(category: String): Flow<Resource<List<NewsItem>>>
}

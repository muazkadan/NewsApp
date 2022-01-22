package net.mouazkaadan.inshort.domain.useacase

import kotlinx.coroutines.flow.Flow
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.data.repository.NewsRepository
import net.mouazkaadan.inshort.utils.Resource
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(category: String): Flow<Resource<List<NewsItem>>> =
        repository.getNews(category = category)
}

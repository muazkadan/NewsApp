package net.mouazkaadan.inshort.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.mouazkaadan.inshort.data.local.NewsItemDao
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.data.network.Api
import net.mouazkaadan.inshort.domain.repository.NewsDataSource
import net.mouazkaadan.inshort.utils.Resource
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private var api: Api,
    private var dao: NewsItemDao
) : NewsDataSource {

    override fun getNews(category: String): Flow<Resource<List<NewsItem>>> = flow {

        val localNews = dao.getNewsItems(category).map { it.toNewsItem() }
        emit(Resource.Loading(data = localNews))

        try {
            val response = api.getNews(category = category)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                if (!result.success) {
                    emit(Resource.Error(message = result.error ?: "Something went wrong"))
                }
                dao.deleteNewsItems(dao.getNewsItems(category))
                dao.insertNewsItems(
                    result.data.map {
                        it.toNewsItemEntity(category)
                    }
                )
            } else {
                emit(Resource.Error(message = response.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Something went wrong"))
        }

        val newLocalNews = dao.getNewsItems(category).map { it.toNewsItem() }
        emit(Resource.Success(data = newLocalNews))
    }
}

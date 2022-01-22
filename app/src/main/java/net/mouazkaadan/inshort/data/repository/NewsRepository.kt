package net.mouazkaadan.inshort.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    private var api: Api
) : NewsDataSource {

    override fun getNews(category: String): Flow<Resource<List<NewsItem>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getNews(category = category)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                if (!result.success) {
                    emit(Resource.Error(message = result.error ?: "Something went wrong"))
                }
                emit(Resource.Success(data = result.data))
            } else {
                emit(Resource.Error(message = response.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Something went wrong"))
        }
    }
}

package net.mouazkaadan.inshort.repository

import net.mouazkaadan.inshort.network.Api
import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import net.mouazkaadan.inshort.utils.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private var api: Api
) : NewsDataSource {

    override suspend fun getNews(category: String): Resource<NewsResponseModel> {
        return try {
            val response = api.getNews(category)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong")
        }
    }
}

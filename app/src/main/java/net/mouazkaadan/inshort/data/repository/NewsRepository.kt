package net.mouazkaadan.inshort.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.data.local.NewsItemDao
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.data.network.Api
import net.mouazkaadan.inshort.domain.mapper.NewsItemMapper
import net.mouazkaadan.inshort.domain.repository.NewsDataSource
import net.mouazkaadan.inshort.utils.Resource
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private var api: Api,
    private var dao: NewsItemDao,
    private val mapper: NewsItemMapper,
    @ApplicationContext private val context: Context
) : NewsDataSource {

    override fun getNews(category: String): Flow<Resource<List<NewsItem>>> = flow {

        val localNews = dao.getNewsItems(category).map { mapper.map(it) }
        emit(Resource.Loading(data = localNews))

        try {
            val response = api.getNews(category = category)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                if (!result.success) {
                    emit(Resource.Error(message = result.error ?: context.getString(R.string.something_went_wrong)))
                }
                dao.deleteNewsItems(dao.getNewsItems(category))
                dao.insertNewsItems(
                    result.data.map {
                        mapper.map(it, category)
                    }
                )
            } else {
                emit(Resource.Error(message = response.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = context.getString(R.string.couldnt_reach_server)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: context.getString(R.string.something_went_wrong)))
        }

        val newLocalNews = dao.getNewsItems(category).map { mapper.map(it) }
        emit(Resource.Success(data = newLocalNews))
    }
}

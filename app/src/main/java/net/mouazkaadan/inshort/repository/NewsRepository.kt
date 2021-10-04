package net.mouazkaadan.inshort.repository

import androidx.lifecycle.MutableLiveData
import net.mouazkaadan.inshort.network.Api
import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private var api: Api
) {
    var newsData = MutableLiveData<NewsResponseModel>()

    suspend fun getNews(category: String) {
        newsData.postValue(api.getNews(category))
    }
}

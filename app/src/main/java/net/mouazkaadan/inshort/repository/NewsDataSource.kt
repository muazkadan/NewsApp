package net.mouazkaadan.inshort.repository

import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import net.mouazkaadan.inshort.utils.Resource

interface NewsDataSource {

    suspend fun getNews(category: String): Resource<NewsResponseModel>
}

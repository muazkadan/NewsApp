package net.mouazkaadan.inshort.di

import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("news")
    suspend fun getNews(
        @Query("category") category: String
    ): NewsResponseModel
}

package net.mouazkaadan.inshort.data.network

import net.mouazkaadan.inshort.data.model.NewsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/news")
    suspend fun getNews(
        @Query("category") category: String
    ): Response<NewsResponseModel>
}

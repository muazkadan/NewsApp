package net.mouazkaadan.inshort.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mouazkaadan.inshort.data.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    val provideLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @Singleton
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOtherOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient): Api =
        Retrofit.Builder().baseUrl("https://inshortsapi.vercel.app")
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(Api::class.java)
}

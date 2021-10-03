package net.mouazkaadan.inshort.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): Api =
        Retrofit.Builder().baseUrl("https://inshortsapi.vercel.app/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(Api::class.java)
}

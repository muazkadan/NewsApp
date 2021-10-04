package net.mouazkaadan.inshort.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mouazkaadan.inshort.network.Api
import net.mouazkaadan.inshort.repository.NewsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun newsRepo(
        api: Api
    ): NewsRepository = NewsRepository(
        api
    )
}

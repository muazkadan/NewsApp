package net.mouazkaadan.inshort.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mouazkaadan.inshort.data.local.NewsItemDatabase
import net.mouazkaadan.inshort.data.network.Api
import net.mouazkaadan.inshort.data.repository.NewsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: Api,
        db: NewsItemDatabase
    ): NewsRepository = NewsRepository(
        api,
        db.dao
    )
}

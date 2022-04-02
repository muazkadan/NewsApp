package net.mouazkaadan.inshort.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.mouazkaadan.inshort.data.local.NewsItemDatabase
import net.mouazkaadan.inshort.data.network.Api
import net.mouazkaadan.inshort.data.repository.NewsRepository
import net.mouazkaadan.inshort.domain.mapper.NewsItemMapper
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: Api,
        db: NewsItemDatabase,
        mapper: NewsItemMapper,
        @ApplicationContext context: Context
    ): NewsRepository = NewsRepository(
        api,
        db.dao,
        mapper,
        context
    )
}

package net.mouazkaadan.inshort.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mouazkaadan.inshort.data.repository.NewsRepository
import net.mouazkaadan.inshort.domain.useacase.GetNewsUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        repository: NewsRepository
    ): GetNewsUseCase = GetNewsUseCase(repository)
}

package net.mouazkaadan.inshort.presentation.newsPage.model

import net.mouazkaadan.inshort.data.model.NewsItem

class NewsUiState(
    val newsItems: List<NewsItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

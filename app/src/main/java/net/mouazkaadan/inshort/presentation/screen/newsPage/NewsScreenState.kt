package net.mouazkaadan.inshort.presentation.screen.newsPage

import net.mouazkaadan.inshort.data.model.NewsItem

data class NewsScreenState(
    val categoryName: String? = null,
    val newsItems: List<NewsItem> = emptyList(),
    val isLoading: Boolean = false,
    val isScrollUp: Boolean = false
)

package net.mouazkaadan.inshort.presentation.screen.newsPage

sealed class NewsDetailsScreenEvent {
    data class UpdateScrollPosition(val position: Int) : NewsDetailsScreenEvent()
}

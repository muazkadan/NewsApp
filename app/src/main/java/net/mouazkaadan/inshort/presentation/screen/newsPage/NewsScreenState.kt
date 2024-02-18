package net.mouazkaadan.inshort.presentation.screen.newsPage

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import net.mouazkaadan.inshort.data.model.NewsItem

data class NewsScreenState(
    val categoryName: String? = null,
    val newsItems: List<NewsItem> = emptyList(),
    val isLoading: Boolean = false,
    val isScrollUp: Boolean = false
)

class NewsScreenStatePreviewParameterProvider : PreviewParameterProvider<NewsScreenState> {
    override val values = sequenceOf(
        NewsScreenState(categoryName = "Category Title", isLoading = true),
        NewsScreenState(
            categoryName = "Category Title",
            newsItems = listOf(
                NewsItem(
                    author = "John Doe",
                    content = "This is a sample news content",
                    date = "20 Sep 2022",
                    imageUrl = "https://example.com/images/news1.png",
                    readMoreUrl = "https://example.com/news1",
                    time = "Monday, 10:30 am",
                    title = "Sample News 1",
                    url = "https://example.com/news1"
                ),

                NewsItem(
                    author = "Jane Smith",
                    content = "This is another sample news content",
                    date = "21 Sep 2022",
                    imageUrl = "https://example.com/images/news2.png",
                    readMoreUrl = "https://example.com/news2",
                    time = "Tuesday, 11:45 am",
                    title = "Sample News 2",
                    url = "https://example.com/news2"
                ),

                NewsItem(
                    author = "Robert Johnson",
                    content = "This is yet another sample news content",
                    date = "22 Sep 2022",
                    imageUrl = "https://example.com/images/news3.png",
                    readMoreUrl = "https://example.com/news3",
                    time = "Wednesday, 01:15 pm",
                    title = "Sample News 3",
                    url = "https://example.com/news3"
                )

            )
        )
    )
}

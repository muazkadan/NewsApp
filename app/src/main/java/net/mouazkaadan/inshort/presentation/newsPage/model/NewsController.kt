package net.mouazkaadan.inshort.presentation.newsPage.model

import com.airbnb.epoxy.EpoxyController
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.databinding.NewsItemListBinding
import net.mouazkaadan.inshort.utils.epoxy.ViewBindingKotlinModel
import net.mouazkaadan.inshort.utils.extensions.loadImage

class NewsController(private val listener: OnNewsClickListener) :
    EpoxyController() {

    var list = emptyList<NewsItem>()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        if (list.isNotEmpty()) {
            list.forEach {
                NewsEpoxyModel(it, listener).id(it.title).addTo(this)
            }
        }
    }

    data class NewsEpoxyModel(
        val newsItem: NewsItem,
        val listener: OnNewsClickListener
    ) : ViewBindingKotlinModel<NewsItemListBinding>(R.layout.news_item_list) {

        override fun NewsItemListBinding.bind() {
            newsTitleTv.text = newsItem.title
            newsContentTv.text = newsItem.content
            newsAuthorTv.text = "${newsItem.author},"
            newsDateTv.text = "${newsItem.date},"
            newsTimeTv.text = newsItem.time

            newsImageView.loadImage(newsItem.imageUrl)

            shareFab.setOnClickListener {
                listener.onShareClick(newsItem)
            }

            root.setOnClickListener {
                listener.onItemClick(newsItem)
            }
        }
    }

    interface OnNewsClickListener {
        fun onShareClick(item: NewsItem)
        fun onItemClick(item: NewsItem)
    }
}

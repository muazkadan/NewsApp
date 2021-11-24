package net.mouazkaadan.inshort.ui.newsPage.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.mouazkaadan.inshort.databinding.NewsItemListBinding
import net.mouazkaadan.inshort.utils.loadImage

class NewsAdapter(val listener: OnNewsClickListener<Data>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var list = ArrayList<Data>()

    fun setNewsList(list: List<Data>) {
        this.list = list as ArrayList<Data>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = list[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NewsViewHolder(private val binding: NewsItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: Data) {
            with(binding) {
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
    }
}

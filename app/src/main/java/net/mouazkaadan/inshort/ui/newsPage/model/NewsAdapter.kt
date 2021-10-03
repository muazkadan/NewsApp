package net.mouazkaadan.inshort.ui.newsPage.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.mouazkaadan.inshort.base.OnItemClickListener
import net.mouazkaadan.inshort.databinding.NewsItemListBinding

class NewsAdapter(val listener: OnItemClickListener<Data>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var list = ArrayList<Data>()

    fun setNewsList(list: List<Data>) {
        this.list = list as ArrayList<Data>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = list[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NewsViewHolder(private val context: Context, private val binding: NewsItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: Data) {
            with(binding) {
                newsTitleTv.text = newsItem.title
                newsContentTv.text = newsItem.content
                newsAuthorTv.text = "${newsItem.author},"
                newsDateTv.text = "${newsItem.date},"
                newsTimeTv.text = newsItem.time

                Glide.with(context).load(newsItem.imageUrl).into(newsImageView)

                shareFab.setOnClickListener {
                    listener.onItemClick(newsItem)
                }
            }
        }
    }
}

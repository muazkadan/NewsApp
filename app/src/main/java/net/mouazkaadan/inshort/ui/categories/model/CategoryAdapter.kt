package net.mouazkaadan.inshort.ui.categories.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.mouazkaadan.inshort.databinding.CategoryItemListBinding

class CategoryAdapter(var list: List<CategoryModel>, val listener: OnCategoryClickListener<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = list[position]
        holder.bind(categoryItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CategoryViewHolder(private val binding: CategoryItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryItem: CategoryModel) {

            binding.categoryNameTv.text = categoryItem.name

            binding.root.setOnClickListener {
                listener.onItemClick(categoryItem)
            }
        }
    }
}

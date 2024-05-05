package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ItemArticleBinding

class ArticleAdapter(private val onArticleClickListener: (String) -> Unit) : ListAdapter<ArticlesItem, ArticleAdapter.ListViewHolder>(DIFF_CALLBACK) {

    class ListViewHolder(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticlesItem, onArticleClickListener: (String) -> Unit) {
            binding.tvItemTitle.text = article.title
            binding.tvItemDesc.text = article.description
            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.imgAvatar)

            binding.root.setOnClickListener {
                article.url?.let { url ->
                    onArticleClickListener(url)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, onArticleClickListener)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.dicoding.asclepius.view.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.adapter.ArticleAdapter
import com.dicoding.asclepius.databinding.ActivityArticleBinding
import com.dicoding.asclepius.helper.ViewModelFactory

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private val articleViewModel: ArticleViewModel by viewModels<ArticleViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecyclerView()
        articleViewModel.getArticle()
        observeViewModel()

        articleViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private val onArticleClickListener: (String) -> Unit = { url ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun showRecyclerView() {
        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter(onArticleClickListener)
        binding.rvArticle.adapter = articleAdapter
    }

    private fun observeViewModel() {
        articleViewModel.article.observe(this) { articles ->
            articles?.let {
                articleAdapter.submitList(it)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
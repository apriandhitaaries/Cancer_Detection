package com.dicoding.asclepius.view.history

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.ListHistoryAdapter
import com.dicoding.asclepius.data.local.PredictionHistory
import com.dicoding.asclepius.databinding.ActivityPredictionHistoryBinding
import com.dicoding.asclepius.helper.ViewModelFactory

class PredictionHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPredictionHistoryBinding
    private val predictionHistoryViewModel by viewModels<PredictionHistoryViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecyclerView()

    }

    private fun showRecyclerView() {
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        val adapter = ListHistoryAdapter(predictionHistoryViewModel)
        binding.rvHistory.adapter = adapter

        showLoading(true)
        predictionHistoryViewModel.getAllPredictionHistory().observe(this) { history ->
            if (history.isEmpty()) {
                showEmptyMessage()
            } else {
                adapter.submitList(history)
            }
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showEmptyMessage() {
        binding.tvNoHistory.visibility = View.VISIBLE
        binding.rvHistory.visibility = View.GONE
        binding.tvNoHistory.text = "History is empty"
    }

}
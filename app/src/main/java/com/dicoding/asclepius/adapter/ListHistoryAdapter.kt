package com.dicoding.asclepius.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dicoding.asclepius.data.local.PredictionHistory
import com.dicoding.asclepius.databinding.ItemRowHistoryBinding
import com.dicoding.asclepius.helper.ViewModelFactory
import com.dicoding.asclepius.view.history.PredictionHistoryViewModel
import java.io.File

class ListHistoryAdapter(private val viewModel: PredictionHistoryViewModel) : ListAdapter<PredictionHistory, ListHistoryAdapter.ListViewHolder>(DIFF_CALLBACK) {

    class ListViewHolder (
        val binding: ItemRowHistoryBinding,
        private val viewModel: PredictionHistoryViewModel
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(history: PredictionHistory) {
            binding.tvItemResult.text = history.result
            binding.tvItemDate.text = history.date
            binding.btnDelete.setOnClickListener {
                viewModel.delete(PredictionHistory(history.result))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PredictionHistory>() {
            override fun areItemsTheSame(oldItem: PredictionHistory, newItem: PredictionHistory): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PredictionHistory, newItem: PredictionHistory): Boolean {
                return oldItem == newItem
            }
        }
    }
}
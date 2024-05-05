package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.PredictionHistory
import com.dicoding.asclepius.data.repository.PredictionHistoryRepository

class PredictionHistoryViewModel(application: Application) :ViewModel() {
    private val mPredictionHistoryRepository: PredictionHistoryRepository = PredictionHistoryRepository(application)

    fun insert (predictionHistory: PredictionHistory) {
        mPredictionHistoryRepository.insert(predictionHistory)
    }

    fun delete (predictionHistory: PredictionHistory) {
        mPredictionHistoryRepository.delete(predictionHistory)
    }

    fun getAllPredictionHistory() = mPredictionHistoryRepository.getAllPredictionHistory()
}
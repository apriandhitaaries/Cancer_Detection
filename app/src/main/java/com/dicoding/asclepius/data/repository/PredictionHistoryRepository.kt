package com.dicoding.asclepius.data.repository

import android.app.Application
import com.dicoding.asclepius.data.local.PredictionHistory
import com.dicoding.asclepius.data.local.PredictionHistoryDao
import com.dicoding.asclepius.data.local.PredictionHistoryDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PredictionHistoryRepository(application: Application) {
    private val mPredictionHistoryDao: PredictionHistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PredictionHistoryDatabase.getDatabase(application)
        mPredictionHistoryDao = db.predictionHistoryDao()
    }

    fun getAllPredictionHistory() = mPredictionHistoryDao.getAllPredictionHistory()

    fun insert(predictionHistory: PredictionHistory) = executorService.execute { mPredictionHistoryDao.insert(predictionHistory) }

    fun delete(predictionHistory: PredictionHistory) = executorService.execute { mPredictionHistoryDao.delete(predictionHistory) }
}
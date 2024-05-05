package com.dicoding.asclepius.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PredictionHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(predictionHistory: PredictionHistory)

    @Delete
    fun delete(predictionHistory: PredictionHistory)

    @Query("SELECT * FROM PredictionHistory")
    fun getAllPredictionHistory(): LiveData<List<PredictionHistory>>
}
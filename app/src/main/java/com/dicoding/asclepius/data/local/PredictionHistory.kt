package com.dicoding.asclepius.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PredictionHistory (
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "result")
    var result: String = "",

    @ColumnInfo(name = "imageUri")
    var imageUri: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable
package com.egsystem.dailyexpenses.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "income_table")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val income_amount: Int,
    val income_category: String,
    val income_description: String,
    val income_startTime: String,
    val imageId: Int)
: Parcelable
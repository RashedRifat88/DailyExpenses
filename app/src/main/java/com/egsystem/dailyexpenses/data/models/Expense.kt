package com.egsystem.dailyexpenses.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val expense_amount: Int,
    val expense_category: String,
    val expense_description: String,
    val expense_startTime: String,
    val imageId: Int)
: Parcelable
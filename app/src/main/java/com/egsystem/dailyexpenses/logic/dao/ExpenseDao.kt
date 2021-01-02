package com.egsystem.dailyexpenses.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.egsystem.dailyexpenses.data.models.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(Expense: Expense)

    @Update
    suspend fun updateExpense(Expense: Expense)

    @Delete
    suspend fun deleteExpense(Expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("DELETE FROM expense_table")
    suspend fun deleteAll()
}
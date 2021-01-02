package com.egsystem.dailyexpenses.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.egsystem.dailyexpenses.data.models.Income

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIncome(Income: Income)

    @Update
    suspend fun updateIncome(Income: Income)

    @Delete
    suspend fun deleteIncome(Income: Income)

    @Query("SELECT * FROM income_table ORDER BY id DESC")
    fun getAllIncomes(): LiveData<List<Income>>

    @Query("DELETE FROM income_table")
    suspend fun deleteAll()
}
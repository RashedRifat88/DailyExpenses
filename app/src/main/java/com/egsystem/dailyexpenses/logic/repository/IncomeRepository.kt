package com.egsystem.dailyexpenses.logic.repository

import androidx.lifecycle.LiveData
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyexpenses.logic.dao.IncomeDao

class IncomeRepository (private val IncomeDao: IncomeDao) {
    val getAllIncomes: LiveData<List<Income>> = IncomeDao.getAllIncomes()

    suspend fun addIncome(Income: Income) {
        IncomeDao.addIncome(Income)
    }

    suspend fun updateIncome(Income: Income) {
        IncomeDao.updateIncome(Income)
    }

    suspend fun deleteIncome(Income: Income) {
        IncomeDao.deleteIncome(Income)
    }

    suspend fun deleteAllIncomes() {
        IncomeDao.deleteAll()
    }


}
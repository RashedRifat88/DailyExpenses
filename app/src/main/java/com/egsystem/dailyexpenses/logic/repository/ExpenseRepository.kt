package com.egsystem.dailyexpenses.logic.repository

import androidx.lifecycle.LiveData
import com.egsystem.dailyexpenses.data.models.Expense
import com.egsystem.dailyexpenses.logic.dao.ExpenseDao

class ExpenseRepository (private val ExpenseDao: ExpenseDao) {
    val getAllExpenses: LiveData<List<Expense>> = ExpenseDao.getAllExpenses()

    suspend fun addExpense(Expense: Expense) {
        ExpenseDao.addExpense(Expense)
    }

    suspend fun updateExpense(Expense: Expense) {
        ExpenseDao.updateExpense(Expense)
    }

    suspend fun deleteExpense(Expense: Expense) {
        ExpenseDao.deleteExpense(Expense)
    }

    suspend fun deleteAllExpenses() {
        ExpenseDao.deleteAll()
    }


}
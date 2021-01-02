package com.egsystem.dailyexpenses.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.egsystem.dailyexpenses.data.models.Expense
import com.egsystem.dailyexpenses.data.database.ExpenseDatabase
import com.egsystem.dailyexpenses.logic.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository
    val getAllExpenses: LiveData<List<Expense>>


    init {
        val ExpenseDao= ExpenseDatabase.getDatabase(application).ExpenseDao()
        repository = ExpenseRepository(ExpenseDao)

        getAllExpenses = repository.getAllExpenses
    }

    fun addExpense(Expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(Expense)
        }
    }

    fun updateExpense(Expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExpense(Expense)
        }
    }

    fun deleteExpense(Expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExpense(Expense)
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExpenses()
        }
    }


}
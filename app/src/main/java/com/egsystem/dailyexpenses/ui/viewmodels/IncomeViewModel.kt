package com.egsystem.dailyexpenses.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.egsystem.dailyexpenses.data.database.ExpenseDatabase
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyexpenses.logic.repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IncomeRepository
    val getAllIncomes: LiveData<List<Income>>


    init {
        val IncomeDao= ExpenseDatabase.getDatabase(application).IncomeDao()
        repository = IncomeRepository(IncomeDao)

        getAllIncomes = repository.getAllIncomes
    }

    fun addIncome(Income: Income) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncome(Income)
        }
    }

    fun updateIncome(Income: Income) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIncome(Income)
        }
    }

    fun deleteIncome(Income: Income) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIncome(Income)
        }
    }

    fun deleteAllIncomes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllIncomes()
        }
    }


}
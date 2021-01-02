package com.egsystem.dailyexpenses.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.egsystem.dailyexpenses.data.models.Expense
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyexpenses.logic.dao.ExpenseDao
import com.egsystem.dailyexpenses.logic.dao.IncomeDao

@Database(entities = [Expense::class, Income::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun ExpenseDao(): ExpenseDao

    abstract fun IncomeDao(): IncomeDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
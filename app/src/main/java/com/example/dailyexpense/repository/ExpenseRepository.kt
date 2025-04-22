package com.example.dailyexpense.repository

import androidx.lifecycle.LiveData
import com.example.dailyexpense.data.Expense
import com.example.dailyexpense.data.ExpenseDao
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    fun getExpenseById(id: Int): Flow<Expense> {
        return expenseDao.getExpenseById(id)
    }
}

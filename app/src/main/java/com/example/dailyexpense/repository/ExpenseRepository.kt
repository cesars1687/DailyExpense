package com.example.dailyexpense.repository

import com.example.dailyexpense.model.Expense
import com.example.dailyexpense.data.ExpenseDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que proporciona una abstracción sobre las operaciones de datos de gastos.
 * Interactúa con ExpenseDao para acceder a la base de datos.
 */
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

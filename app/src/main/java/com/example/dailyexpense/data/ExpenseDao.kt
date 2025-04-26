package com.example.dailyexpense.data

import androidx.room.*
import com.example.dailyexpense.model.Expense
import kotlinx.coroutines.flow.Flow

/**
 * DAO que define las operaciones CRUD para la entidad Expense.
 * Permite insertar, eliminar y consultar gastos usando Room.
 */
@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY id DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpenseById(id: Int): Flow<Expense>
}

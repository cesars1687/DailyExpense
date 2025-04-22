package com.example.dailyexpense.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyexpense.data.ExpenseDatabase
import com.example.dailyexpense.repository.ExpenseRepository

object AppViewModelFactory {
    fun provideExpenseViewModelFactory(context: Context): ViewModelProvider.Factory {
        val dao = ExpenseDatabase.getDatabase(context).expenseDao()
        val repository = ExpenseRepository(dao)
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ExpenseViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

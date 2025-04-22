package com.example.dailyexpense.repository

import com.example.dailyexpense.data.Category
import com.example.dailyexpense.data.CategoryDao
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {
    val categories: Flow<List<Category>> = categoryDao.getAll()

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }
}

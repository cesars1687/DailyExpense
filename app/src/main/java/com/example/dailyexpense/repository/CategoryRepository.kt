package com.example.dailyexpense.repository

import com.example.dailyexpense.model.Category
import com.example.dailyexpense.data.CategoryDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que proporciona una abstracción sobre las operaciones de datos de categorías.
 * Interactúa con CategoryDao para gestionar las categorías en la base de datos.
 */
class CategoryRepository(private val categoryDao: CategoryDao) {
    val categories: Flow<List<Category>> = categoryDao.getAll()

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }
}

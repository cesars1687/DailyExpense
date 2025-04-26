package com.example.dailyexpense.data

import androidx.room.*
import com.example.dailyexpense.model.Category
import kotlinx.coroutines.flow.Flow

/**
 * DAO que define las operaciones CRUD para la entidad Category.
 * Permite insertar y consultar categorías usando Room.
 */
@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAll(): Flow<List<Category>>

    @Delete
    suspend fun delete(category: Category)
}

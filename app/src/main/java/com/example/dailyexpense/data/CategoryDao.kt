package com.example.dailyexpense.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAll(): Flow<List<Category>>

    @Delete
    suspend fun delete(category: Category)
}

// Modelo de datos Category.kt
package com.example.dailyexpense.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Ignore

/**
 * Entidad Category que representa una categoría de gasto en la base de datos local.
 * Cada categoría tiene un nombre y un icono asociado.
 */
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val iconName: String // guardaremos el nombre del icono
) {
    @Ignore
    var icon: ImageVector? = null
}

package com.example.dailyexpense.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Ignore
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.ShoppingCart

/**
 * Entidad Expense que representa un gasto en la base de datos local.
 * Cada gasto incluye nombre, monto, categoría y fecha.
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val category: String,
    val date: String
) {
    // Este campo no se guarda en la base de datos, solo se usa para la UI
    @Ignore
    var icon: ImageVector = androidx.compose.material.icons.Icons.Default.ShoppingCart

    companion object {
        fun getIconForCategory(category: String): ImageVector {
            return when (category.lowercase()) {
                "shopping" -> Icons.Filled.ShoppingCart
                "transport" -> Icons.Filled.DirectionsCar
                "food" -> Icons.Filled.Fastfood
                else -> Icons.Filled.AttachMoney
            }
        }
    }
}

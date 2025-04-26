package com.example.dailyexpense.navigation

/**
 * Clase de navegación que define las rutas (Screens) dentro de la aplicación.
 * Se utiliza para estructurar la navegación entre las distintas pantallas.
 */
sealed class Screen(val route: String) {
    object ExpensesList : Screen("expenses_list") // Pantalla de lista de gastos
    object AddExpense : Screen("add_expense") // Pantalla de agregar gasto
    object Summary : Screen("summary") // Pantalla de resumen
    object Categories : Screen("categories") // Pantalla de resumen
}

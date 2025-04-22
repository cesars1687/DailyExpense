package com.example.dailyexpense.navigation

sealed class Screen(val route: String) {
    object ExpensesList : Screen("expenses_list") // Pantalla de lista de gastos
    object AddExpense : Screen("add_expense") // Pantalla de agregar gasto
    object Summary : Screen("summary") // Pantalla de resumen
    object Categories : Screen("categories") // Pantalla de resumen
}

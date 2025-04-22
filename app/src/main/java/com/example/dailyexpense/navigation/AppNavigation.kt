package com.example.dailyexpense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailyexpense.ui.screens.addExpense.AddExpenseScreen
import com.example.dailyexpense.ui.screens.expensesList.ExpensesListScreen
import com.example.dailyexpense.ui.screens.summary.SummaryScreen
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.dailyexpense.ui.screens.categories.CategoryScreen
import com.example.dailyexpense.viewmodel.CategoryViewModel
import com.example.dailyexpense.viewmodel.ExpenseViewModel

@Composable
fun AppNavigation(navController: NavHostController, expenseViewModel: ExpenseViewModel,  categoryViewModel: CategoryViewModel, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.ExpensesList.route,
        modifier = modifier // 🔥 Aplica el modifier
    ) {
        composable(Screen.ExpensesList.route) { ExpensesListScreen(navController, expenseViewModel, categoryViewModel) }
        composable(Screen.AddExpense.route) { AddExpenseScreen(navController, expenseViewModel, categoryViewModel) }
        composable(Screen.Summary.route) { SummaryScreen(navController, expenseViewModel) }
        composable(Screen.Categories.route) { CategoryScreen(navController, expenseViewModel) }
    }
}

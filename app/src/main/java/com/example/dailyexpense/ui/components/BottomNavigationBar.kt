package com.example.dailyexpense.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailyexpense.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Expenses : BottomNavItem(Screen.ExpensesList.route, Icons.Filled.List, "Expenses")
    object AddExpense : BottomNavItem(Screen.AddExpense.route, Icons.Filled.Add, "Add")
    object Summary : BottomNavItem(Screen.Summary.route, Icons.Filled.Summarize, "Summary")
    object Categories : BottomNavItem(Screen.Categories.route, Icons.Filled.Category, "Category")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Expenses,
        BottomNavItem.AddExpense,
        BottomNavItem.Summary,
        BottomNavItem.Categories
    )

    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

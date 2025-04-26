// CustomBottomNavigationBar.kt
package com.example.dailyexpense.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dailyexpense.navigation.Screen
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Composable que representa la barra de navegación inferior personalizada.
 * Permite la navegación entre Summary, Expenses, AddExpense y AddCategory.
 */
@Composable
fun CustomBottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavigationItem("Summary", Icons.Default.BarChart, Screen.Summary.route),
        NavigationItem("Expenses", Icons.Default.Checklist, Screen.ExpensesList.route),
        NavigationItem("Add", Icons.Default.Add, Screen.AddExpense.route),
        NavigationItem("Category", Icons.Default.Category, Screen.Categories.route)
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(bottom = 16.dp)
            .padding(top = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF262650)),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach { item ->

                    IconButton(onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (currentRoute == item.route) Color(0xFF9B6CFF) else Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                }
            }
        }

        // FAB central flotante
        /*FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddExpense.route)
            },
            containerColor = Color(0xFF7A42F4),
            contentColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .size(56.dp)
                .offset(y = (-28).dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(32.dp))
        }*/
    }
}

// Helper class
data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

package com.example.dailyexpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.dailyexpense.data.ExpenseDatabase
import com.example.dailyexpense.repository.ExpenseRepository
import com.example.dailyexpense.repository.CategoryRepository
import com.example.dailyexpense.navigation.AppNavigation
import com.example.dailyexpense.ui.components.BottomNavigationBar
import com.example.dailyexpense.ui.components.CustomBottomNavigationBar
import com.example.dailyexpense.ui.theme.DailyExpenseTheme
import com.example.dailyexpense.viewmodel.ExpenseViewModel
import com.example.dailyexpense.viewmodel.ExpenseViewModelFactory
import com.example.dailyexpense.viewmodel.CategoryViewModel
import com.example.dailyexpense.viewmodel.CategoryViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DailyExpenseTheme {
                val navController = rememberNavController()

                // Instanciar la base de datos y el repositorio
                val database = ExpenseDatabase.getDatabase(this)
                val expenseRepository = ExpenseRepository(database.expenseDao())
                val categoryRepository = CategoryRepository(database.categoryDao())

                val expenseViewModel: ExpenseViewModel = viewModel(
                    factory = ExpenseViewModelFactory(expenseRepository)
                )
                val categoryViewModel: CategoryViewModel = viewModel(
                    factory = CategoryViewModelFactory(categoryRepository)
                )


                Scaffold(
                    containerColor = Color(0xFF0D0C1F),
                    bottomBar = { CustomBottomNavigationBar(navController) }
                ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        expenseViewModel = expenseViewModel,
                        categoryViewModel = categoryViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


    }
}

package com.example.dailyexpense.ui.screens.expensesList

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dailyexpense.data.Category
import com.example.dailyexpense.viewmodel.AppViewModelFactory
import com.example.dailyexpense.viewmodel.ExpenseViewModel
import com.example.dailyexpense.viewmodel.CategoryViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.dailyexpense.utils.IconWithColor
import com.example.dailyexpenses.R

@Composable
fun ExpensesListScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel,
    categoryViewModel: CategoryViewModel
) {
    val context = LocalContext.current
    val viewModel: ExpenseViewModel = viewModel(
        factory = AppViewModelFactory.provideExpenseViewModelFactory(context)
    )
    val expenses by viewModel.expenses.collectAsState()
    val categories by categoryViewModel.categories.collectAsState(initial = emptyList())

    val backgroundGradient = Brush.radialGradient(
        colors = listOf(Color(0xFF56645C), Color(0xFF1F1C2C))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.expenses),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(expenses) { expense ->
                val category: Category? = categories.find { it.name == expense.category }
                val icon = getIconByName(category?.iconName ?: "AttachMoney")
                val iconColor = getIconColorByName(category?.iconName ?: "AttachMoney")

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A3C)),
                    shape = RoundedCornerShape(30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Icono dentro de círculo de color
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(color = iconColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = expense.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                            Text(
                                text = expense.date,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        // Monto encerrado con borde
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 0.5.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "S/ ${String.format("%.2f", expense.amount)}",
                                color = iconColor,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 22.sp
                            )
                        }
                    }
                }
            }
        }

    }
}



fun getIconByName(name: String): ImageVector {
    return when (name) {
        "AttachMoney" -> Icons.Default.AttachMoney
        "LocalCafe" -> Icons.Default.LocalCafe
        "MusicNote" -> Icons.Default.MusicNote
        "DirectionsCar" -> Icons.Default.DirectionsCar
        "Movie" -> Icons.Default.Movie
        "FitnessCenter" -> Icons.Default.FitnessCenter
        "School" -> Icons.Default.School
        "Computer" -> Icons.Default.Computer
        "Work" -> Icons.Default.Work
        "SportsEsports" -> Icons.Default.SportsEsports
        "BeachAccess" -> Icons.Default.BeachAccess
        "ShoppingCart" -> Icons.Default.ShoppingCart
        "Home" -> Icons.Default.Home
        "LocalBar" -> Icons.Default.LocalBar
        "Fastfood" -> Icons.Default.Fastfood
        "EmojiEvents" -> Icons.Default.EmojiEvents
        "DirectionsBus" -> Icons.Default.DirectionsBus
        "Phone" -> Icons.Default.Phone
        "LocalGroceryStore" -> Icons.Default.LocalGroceryStore
        "Build" -> Icons.Default.Build
        else -> Icons.Default.AttachMoney
    }
}

fun getIconColorByName(name: String): Color {
    return when (name) {
        "AttachMoney" -> Color(0xFF388E3C) // Verde
        "LocalCafe" -> Color(0xFF6D4C41)   // Marrón
        "MusicNote" -> Color(0xFF7B1FA2)   // Púrpura
        "DirectionsCar" -> Color(0xFF0288D1) // Celeste
        "Movie" -> Color(0xFF512DA8)
        "FitnessCenter" -> Color(0xFFD32F2F)
        "School" -> Color(0xFFF57C00)
        "Computer" -> Color(0xFF1976D2)
        "Work" -> Color(0xFF455A64)
        "SportsEsports" -> Color(0xFF303F9F)
        "BeachAccess" -> Color(0xFF009688)
        "ShoppingCart" -> Color(0xFF7CB342)
        "Home" -> Color(0xFF5D4037)
        "LocalBar" -> Color(0xFF8E24AA)
        "Fastfood" -> Color(0xFFE64A19)
        "EmojiEvents" -> Color(0xFFFFC107)
        "DirectionsBus" -> Color(0xFF0097A7)
        "Phone" -> Color(0xFF43A047)
        "LocalGroceryStore" -> Color(0xFF689F38)
        "Build" -> Color(0xFF616161)
        else -> Color.Gray
    }
}
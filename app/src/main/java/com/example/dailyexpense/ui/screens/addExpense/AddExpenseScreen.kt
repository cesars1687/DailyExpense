package com.example.dailyexpense.ui.screens.addExpense

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dailyexpense.data.Expense
import com.example.dailyexpense.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.navigation.NavHostController
import com.example.dailyexpense.viewmodel.CategoryViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import com.example.dailyexpenses.R

@SuppressLint("SimpleDateFormat")
@Composable
fun AddExpenseScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel,
    categoryViewModel: CategoryViewModel
) {
    val name = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }

    val categories by categoryViewModel.categories.collectAsState(initial = emptyList())
    val categoryNames = categories.map { it.name }

    val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
    val backgroundGradient = Brush.radialGradient(
        colors = listOf(Color(0xFF56645C), Color(0xFF1F1C2C))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                backgroundGradient
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add Expense",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        CustomTextInputCard(label = stringResource(id = R.string.expense_name), value = name)
        CustomTextInputCard(label = stringResource(id = R.string.amount), value = amount)
        CustomDropdownCard(label = stringResource(id = R.string.category), selectedItem = selectedCategory, items = categoryNames)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(id = R.string.date), fontSize = 12.sp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = currentDate, color = Color.White, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (name.value.isNotBlank() && amount.value.isNotBlank() && selectedCategory.value.isNotBlank()) {
                    expenseViewModel.addExpense(
                        Expense(
                            name = name.value,
                            amount = amount.value.toDoubleOrNull() ?: 0.0,
                            category = selectedCategory.value,
                            date = currentDate
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
        ) {
            Text(stringResource(id = R.string.save_expense), color = Color.White, fontSize = 18.sp)
        }
    }
}



@Composable
fun CustomInputCard(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label.uppercase(), fontSize = 12.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = value, color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun CustomTextInputCard(label: String, value: MutableState<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label.uppercase(), fontSize = 12.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value.value,
                onValueChange = { value.value = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF9C27B0),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color(0xFF9C27B0),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color(0xFF9C27B0),
                    unfocusedLabelColor = Color.Gray
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp)
            )
        }
    }
}

@Composable
fun CustomDropdownCard(
    label: String,
    selectedItem: MutableState<String>,
    items: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label.uppercase(), fontSize = 12.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(6.dp))
            Box {
                Text(
                    text = selectedItem.value.ifBlank { stringResource(id = R.string.select_category) },
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .padding(end = 16.dp)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selectedItem.value = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
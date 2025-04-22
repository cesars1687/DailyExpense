package com.example.dailyexpense.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.CardDefaults

@Composable
fun ExpenseCard(
    name: String,
    amount: Double,
    icon: ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = icon,
                    contentDescription = name,
                    tint = Color(0xFF1A73E8) // Color azul bonito
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "$${amount}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A73E8)
            )
        }
    }
}

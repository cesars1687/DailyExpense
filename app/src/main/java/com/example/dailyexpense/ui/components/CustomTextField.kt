package com.example.dailyexpense.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun CustomTextField(
    valueState: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
        modifier = modifier
    )
}

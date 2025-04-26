package com.example.dailyexpense.ui.screens.summary

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dailyexpense.viewmodel.AppViewModelFactory
import com.example.dailyexpense.viewmodel.ExpenseViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.stringResource
import com.example.dailyexpense.ui.theme.ContainerColor
import com.github.mikephil.charting.components.Legend
import com.example.dailyexpenses.R

/**
 * Pantalla de resumen que muestra:
 * - Un Card con el gasto total acumulado.
 * - Un gráfico PieChart con los gastos agrupados por categoría.
 * Los datos se obtienen del ExpenseViewModel.
 */
@Composable
fun SummaryScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel
) {
    val context = LocalContext.current
    val viewModel: ExpenseViewModel = viewModel(
        factory = AppViewModelFactory.provideExpenseViewModelFactory(context)
    )
    //val expenses by expenseViewModel.expenses.collectAsState(initial = emptyList())
    val expensesState = expenseViewModel.expenses.collectAsState()
    val expenses = expensesState.value
    val totalAmount = expenses.sumOf { it.amount }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ContainerColor)
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Gasto Total",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "S/ %.2f".format(totalAmount),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // 🟢 GRÁFICO PIE SOLO SI HAY DATOS
        if (expenses.isNotEmpty()) {
            val categoryTotals = expenses
                .groupBy { it.category }
                .mapValues { entry ->
                    entry.value.sumOf { it.amount }.toFloat()
                }

            val pieEntries = categoryTotals.map { PieEntry(it.value, it.key) }

            val dataSet = PieDataSet(pieEntries, stringResource(id = R.string.expenses_by_category))
            dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
            dataSet.valueTextColor = AndroidColor.WHITE
            dataSet.valueTextSize = 20f

            val pieData = PieData(dataSet)

            AndroidView(
                factory = { context ->
                    PieChart(context).apply {
                        this.data = pieData
                        setEntryLabelColor(AndroidColor.WHITE)
                        setEntryLabelTextSize(25f)
                        setDrawEntryLabels(false)
                        setUsePercentValues(false)
                        setDrawHoleEnabled(true)
                        setHoleColor(AndroidColor.TRANSPARENT)
                        setCenterText("Resumen de Gastos")
                        setCenterTextSize(16f)
                        setCenterTextColor(AndroidColor.WHITE)



                        description.isEnabled = false
                        legend.textSize = 14f
                        legend.textColor = AndroidColor.WHITE
                        legend.formSize = 14f
                        legend.isWordWrapEnabled = true
                        legend.orientation = Legend.LegendOrientation.HORIZONTAL
                        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                        legend.yEntrySpace = 12f
                        legend.xEntrySpace = 12f

                        setBackgroundColor("#0D0C1F".toColorInt())
                        animateY(1000)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
        } else {
            Text(
                text = "No hay datos para mostrar.",
                color = Color.Gray,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}

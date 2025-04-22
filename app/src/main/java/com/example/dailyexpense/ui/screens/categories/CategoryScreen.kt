package com.example.dailyexpense.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dailyexpense.data.ExpenseDatabase
import com.example.dailyexpense.repository.CategoryRepository
import com.example.dailyexpense.viewmodel.CategoryViewModel
import com.example.dailyexpense.viewmodel.CategoryViewModelFactory
import com.example.dailyexpense.viewmodel.ExpenseViewModel
import androidx.compose.ui.res.stringResource
import com.example.dailyexpenses.R

@Composable
fun CategoryScreen(
    navController: NavHostController,
    expenseViewModel: ExpenseViewModel
) {
    val context = LocalContext.current
    val db = ExpenseDatabase.getDatabase(context)
    val repository = CategoryRepository(db.categoryDao())
    val viewModel: CategoryViewModel = viewModel(
        factory = CategoryViewModelFactory(repository)
    )

    val categoryName = remember { mutableStateOf("") }
    val selectedIcon = remember { mutableStateOf("Fastfood") }

    val snackbarHostState = remember { SnackbarHostState() }
    val showSnackbar = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val categories by viewModel.categories.collectAsState(emptyList())

    val iconOptions = listOf(
        "AttachMoney", "LocalCafe", "MusicNote", "DirectionsCar", "Movie", "FitnessCenter",
        "School", "Computer", "Work", "SportsEsports", "BeachAccess", "ShoppingCart",
        "Home", "LocalBar", "Fastfood", "EmojiEvents", "DirectionsBus", "Phone",
        "LocalGroceryStore", "Build"
    )

    if (showSnackbar.value) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar("Category saved successfully!")
            showSnackbar.value = false
        }
    }
    val backgroundGradient = Brush.radialGradient(
        colors = listOf(Color(0xFF56645C), Color(0xFF1F1C2C))
    )
    Scaffold(
        containerColor = Color(0xFF0D0C1F),
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .background(brush = backgroundGradient)
        ) {
            Text(
                text = "Add Category",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(id = R.string.category_name), fontSize = 12.sp, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(6.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = categoryName.value,
                        onValueChange = { categoryName.value = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFF9C27B0),
                            unfocusedIndicatorColor = Color.Gray,
                            cursorColor = Color(0xFF9C27B0),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        textStyle = LocalTextStyle.current.copy(fontSize = 18.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = stringResource(id = R.string.choose_icon), fontSize = 12.sp, color = Color.LightGray)

            IconPicker(iconOptions, selectedIcon)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (categoryName.value.isNotBlank()) {
                        viewModel.addCategory(
                            name = categoryName.value,
                            iconName = selectedIcon.value
                        )
                        categoryName.value = ""
                        keyboardController?.hide()
                        showSnackbar.value = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.save_category))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.saved_category),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2B5B))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = getIconByName(category.iconName),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = category.name,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun IconPicker(
    iconOptions: List<String>,
    selectedIcon: MutableState<String>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(iconOptions) { iconName ->
            IconToggleButton(
                checked = selectedIcon.value == iconName,
                onCheckedChange = {
                    selectedIcon.value = iconName
                }
            ) {
                Icon(
                    imageVector = getIconByName(iconName),
                    contentDescription = iconName,
                    tint = if (selectedIcon.value == iconName) MaterialTheme.colorScheme.primary else Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

fun getIconByName(name: String): ImageVector = when (name) {
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
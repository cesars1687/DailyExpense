package com.example.dailyexpense.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dailyexpense.model.Category
import com.example.dailyexpense.repository.CategoryRepository
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de manejar las operaciones relacionadas con las categorías (Categories).
 * Permite insertar nuevas categorías y consultar las existentes.
 */
class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    val categories = repository.categories

    fun addCategory(name: String, iconName: String) {
        viewModelScope.launch {
            repository.insert(Category(name = name, iconName = iconName))
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            repository.delete(category)
        }
    }
}

class CategoryViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

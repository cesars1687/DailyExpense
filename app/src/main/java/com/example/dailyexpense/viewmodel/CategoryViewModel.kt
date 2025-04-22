package com.example.dailyexpense.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dailyexpense.data.Category
import com.example.dailyexpense.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

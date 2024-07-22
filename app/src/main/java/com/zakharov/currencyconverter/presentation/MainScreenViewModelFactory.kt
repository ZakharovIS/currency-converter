package com.zakharov.currencyconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zakharov.currencyconverter.data.Repository
import javax.inject.Inject

class MainScreenViewModelFactory
@Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(repository) as T
    }
}
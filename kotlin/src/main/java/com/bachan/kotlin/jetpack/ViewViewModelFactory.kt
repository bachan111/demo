package com.bachan.kotlin.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewViewModel(countReserved) as T
    }
}
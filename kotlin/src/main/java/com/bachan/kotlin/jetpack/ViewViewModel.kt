package com.bachan.kotlin.jetpack

import androidx.lifecycle.ViewModel

class ViewViewModel(countReserved:Int):ViewModel() {
    var counter = countReserved
}
package com.example.core.network.ext.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


open class BaseViewModel: ViewModel() {
    val loading = MutableLiveData<Boolean>()
}
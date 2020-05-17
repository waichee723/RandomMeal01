package com.waichee.randommeal01.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel: ViewModel() {

    private val _navigateToMealDetail = MutableLiveData<Boolean?>()
    val navigateToMealDetail: LiveData<Boolean?>
        get() = _navigateToMealDetail


    fun displayMealDetail() {
        _navigateToMealDetail.value = true
    }

    fun displayMealDetailComplete() {
        _navigateToMealDetail.value = null
    }
}
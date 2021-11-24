package com.example.newarchplayground.ui.propertydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : ViewModel() {

    init {
        viewModelScope.launch { getProperties() }
    }

    private suspend fun getProperties() {
        propertyRepository.getProperties().collect {
            when (it) {
                is ApiResult.Success -> {}
                is ApiResult.Error -> {}
                ApiResult.Loading -> {}
            }
        }
    }
}
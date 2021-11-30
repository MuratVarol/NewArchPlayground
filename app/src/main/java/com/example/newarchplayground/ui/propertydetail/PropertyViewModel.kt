package com.example.newarchplayground.ui.propertydetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.local.preferences.PreferencesRepository
import com.example.newarchplayground.data.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            getProperties()
            preferencesRepository.editName("burak").collect {
                Log.d("PropertyViewModel: edit: ", it::class.simpleName.toString())
            }
            preferencesRepository.getPrefName().collect {
                Log.d("PropertyViewModel: collect: ",
                    it::class.simpleName.toString()
                        .plus(" ${(it as? ApiResult.Success)?.data ?: ""}")
                )
            }
        }
    }

    private suspend fun getProperties() {
        propertyRepository.getProperties().flowOn(Dispatchers.Main).collect {
            when (it) {
                is ApiResult.Success -> {}
                is ApiResult.Error -> {}
                ApiResult.Loading -> {}
            }
        }
    }
}
package com.example.newarchplayground.ui.propertydetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.local.preferences.PreferencesRepository
import com.example.newarchplayground.data.repository.PropertyRepository
import com.example.newarchplayground.data.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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
            preferencesRepository.getPrefName()
                .onEach { data ->
                    Log.d("PropertyViewModel: onEach: ",data.toString())
                    data.either(::handleError, ::handleSuccess, ::showLoading)
                }
                .catch { e -> e.printStackTrace() }
                .launchIn(viewModelScope)
        }
    }

    private fun showLoading() {
        Log.d("PropertyViewModel: collect: Loading","")
    }

    private fun handleSuccess(s: String?) {
        Log.d("PropertyViewModel: collect: Success","$s")
    }

    private fun handleError(failure: Failure) {
        Log.d("PropertyViewModel: collect: Failure","")
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
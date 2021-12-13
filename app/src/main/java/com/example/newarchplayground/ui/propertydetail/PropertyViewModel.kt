package com.example.newarchplayground.ui.propertydetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.local.preferences.PreferencesRepository
import com.example.newarchplayground.data.repository.PropertyRepository
import com.google.gson.internal.LinkedTreeMap
import com.example.newarchplayground.data.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        }

        preferencesRepository.editName("burak")
            .onEach {
                Log.d("PropertyViewModel: edit: ", it::class.simpleName.toString())
            }
            .launchIn(viewModelScope)

        preferencesRepository.getPrefName()
            .onEach { data ->
                Log.d("PropertyViewModel: onEach: ", data.toString())
                data.either(::handleError, ::handleSuccess, ::showLoading)
            }
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }

    private fun showLoading() {
        Log.d("PropertyViewModel: Loading", "")
    }

    private fun handleSuccess(s: String?) {
        Log.d("PropertyViewModel: Success", "$s")
    }

    private fun handleError(failure: Failure) {
        Log.d("PropertyViewModel: Failure", "")
    }

    private suspend fun getProperties() {

    }
}
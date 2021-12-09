package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.ui.base.BaseViewModel
import com.example.newarchplayground.ui.base.UiState
import com.example.newarchplayground.ui.base.successData
import com.example.newarchplayground.ui.delegate.snackbar.CanDisplaySnackBar
import com.example.newarchplayground.ui.delegate.snackbar.CanDisplaySnackBarImpl
import com.example.newarchplayground.ui.sample.usecase.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import javax.inject.Inject


@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : BaseViewModel<SampleContract.State>(), CanDisplaySnackBar by CanDisplaySnackBarImpl() {

    init {
        loadList()
    }

    override val exceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, exception ->

        }
    private fun loadList() {
        safeLaunch {
            delay(2000)
            val list = sampleUseCase.invoke()
            updateUiState {
                UiState.Success(SampleContract.State(list))
            }
        }
    }

    fun onSampleButtonClick(text: String) {
        val currentList = currentState.successData.list.toMutableList()
        currentList.remove(text)
        updateUiSuccessState {
            it.copy(
                list = currentList
            )
        }
        if (currentList.isEmpty()) showSnackBar("List is empty!")
    }
}
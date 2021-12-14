package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.data.usecase.SampleUseCase
import com.example.newarchplayground.ui.common.BaseSateViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.delegate.SnackbarControllerImpl
import com.example.newarchplayground.ui.delegate.ToastControllerImpl
import com.example.newarchplayground.ui.delegate.snackbar.ISnackBarController
import com.example.newarchplayground.ui.delegate.toast.IToastController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


data class SampleUIState(
    val list: List<String> = emptyList(),
)

@HiltViewModel
class SampleStateViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : BaseSateViewModel<SampleUIState>(initialState = UiState.Loading),
    ISnackBarController by SnackbarControllerImpl(), IToastController by ToastControllerImpl() {

    init {
        loadList()
    }

    private fun loadList() {
        safeLaunch {
            delay(2000)
            sampleUseCase.invoke(this) { state ->
                updateUiState { state }
            }
        }
    }

    val onSampleButtonClick: (String) -> Unit = { text ->
        val currentList = currentState.successData.list.toMutableList()
        currentList.remove(text)

        updateUiState {
            UiState.Success(
                currentState.successData.copy(
                    list = currentList
                )
            )
        }
        showToast("$text is deleted!")
        if (currentList.isEmpty()) showSnackBar("List is empty!")
    }
}
package com.example.newarchplayground.ui.sample

import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.usecase.SampleUseCase
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.BaseSateViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.delegate.snackbar.CanDisplaySnackBar
import com.example.newarchplayground.ui.delegate.snackbar.CanDisplaySnackBarImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SampleUIState2(
    val list: List<String> = emptyList(),
)

@HiltViewModel
class SampleStateViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : BaseSateViewModel<SampleUIState2>(initialState = UiState.Loading),
    CanDisplaySnackBar by CanDisplaySnackBarImpl() {

    init {
        loadList()
    }

    private fun loadList() {
        safeLaunch {
            delay(2000)
            sampleUseCase.invoke()
                .onEach { data ->
                    updateUiState {
                        flowMap(data) {
                            SampleUIState2(list = it)
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun <DATA, RESULT>flowMap(data: DataResult<Failure, DATA>, onSuccess: (DATA) -> RESULT): UiState<RESULT> {
        return data.either(
            fnError = {
                UiState.Failure(it.localizedMessage ?: "")
            },
            fnSuccess = {
                UiState.Success(
                    onSuccess(it)
                )
            },
            fnLoading = {
                UiState.Loading
            }
        )
    }

    fun onSampleButtonClick(text: String) {
        val currentList = currentState.successData.list.toMutableList()
        currentList.remove(text)

        updateUiState {
            UiState.Success(
                it.successData.copy(
                    list = currentList
                )
            )
        }
        if (currentList.isEmpty()) showSnackBar("List is empty!")
    }
}
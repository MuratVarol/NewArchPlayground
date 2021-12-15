package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.data.usecase.SampleUseCase
import com.example.newarchplayground.ui.common.BaseViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


data class SampleUIState(
    val list: List<String> = emptyList(),
)

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : BaseViewModel<SampleUIState>() {

    init {
        loadList()
    }

    private fun loadList() {
        safeLaunch {
            delay(2000)
            sampleUseCase().onEach{
                it.either(
                    fnLoading = {},
                    fnError = {},
                    fnSuccess = { list ->
                        updateUiState(
                            UiState.Success(
                                currentState.successData.copy(
                                    list = list
                                )
                            )
                        )
                    }
                )
            }
        }
    }

    val onSampleButtonClick: (String) -> Unit = { text ->
        val currentList = currentState.successData.list.toMutableList()
        currentList.remove(text)

        updateUiState (
            UiState.Success(
                currentState.successData.copy(
                    list = currentList
                )
            )
        )
        showToast("$text is deleted!")
        if (currentList.isEmpty()) showSnackBar("List is empty!")
    }
}
package com.example.newarchplayground.ui.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.usecase.SampleUseCase
import com.example.newarchplayground.ui.delegate.SnackbarControllerImpl
import com.example.newarchplayground.ui.delegate.snackbar.ISnackBarController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SampleUIState3(
    val list: List<String> = emptyList(),
    val errorMessage: String? = null,
    val loading: Boolean = true
)

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : BaseViewModel<SampleUIState3>(initialState = SampleUIState3()),
    ISnackBarController by SnackbarControllerImpl() {

    init {
        loadList()
    }

    private fun loadList() {
        safeLaunch {
            delay(2000)
//            sampleUseCase.invoke {
//                updateUiState { it }
//            }
//                .onEach { data ->
//                    updateUiState {
//                        data.either(
//                            fnError = {
//                                currentState.copy(
//                                    errorMessage = it.localizedMessage,
//                                    loading = false
//                                )
//                            },
//                            fnSuccess = {
//                                currentState.copy(
//                                    list = it,
//                                    loading = false
//                                )
//                            },
//                            fnLoading = {
//                                currentState.copy(loading = true)
//                            }
//                        )
//                    }
//                }
//                .launchIn(viewModelScope)
        }
    }

    fun onSampleButtonClick(text: String) {
        val currentList = currentState.list.toMutableList()
        currentList.remove(text)

        updateUiState {
            it.copy(
                list = currentList
            )
        }
        if (currentList.isEmpty()) showSnackBar("List is empty!")
    }
}

abstract class BaseViewModel<STATE>(
    private val initialState: STATE,
) : ViewModel() {

    // Get Current State
    val currentState: STATE
        get() = uiState.value

    private val _uiState: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    val loadingHandler: (Boolean) -> Unit = {

    }
    val failureHandler: (String) -> Unit = {

    }

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    fun updateUiState(updateFunc: (STATE) -> STATE) {
        _uiState.update(updateFunc)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}
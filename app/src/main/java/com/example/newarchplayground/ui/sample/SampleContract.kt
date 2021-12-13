package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.ui.common.UiEffect
import com.example.newarchplayground.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow

sealed class SampleScreenState: BaseState() {

    object Loading : SampleScreenState()

    data class ListState(
        val list: List<String>
    ) : SampleScreenState()

    data class ButtonState(
        val color: String,
        val enabled: Boolean
    ): SampleScreenState()
}

sealed class BaseState {
    object Loading : BaseState()
    data class Failure(val message: String) : BaseState()
}

typealias StateHolder<T> = StateFlow<UiState<T>>

sealed class Effect : UiEffect
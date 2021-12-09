package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.ui.common.UiEffect

class SampleContract {
    data class State(
        val list: List<String>
    )

    sealed class Effect : UiEffect
}
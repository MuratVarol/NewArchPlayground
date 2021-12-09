package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.ui.base.UiEffect

class SampleContract {
    data class State(
        val list: List<String>
    )

    sealed class Effect : UiEffect
}
package com.example.newarchplayground.ui.propertylist

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.ui.common.UiState

class PropertyListContract {

    data class State(
        val propertyListState: UiState<List<PropertyUiModel>?>,
        val nameList: UiState<List<String>?>
    )
}
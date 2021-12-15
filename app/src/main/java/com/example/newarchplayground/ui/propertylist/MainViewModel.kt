package com.example.newarchplayground.ui.propertylist

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import com.example.newarchplayground.ui.common.BaseViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


data class MainScreenState(
    val propertyList: List<PropertyUiModel> = emptyList(),
    val isSwipeRefreshing: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase,
    private val repo: PropertyRepositoryImp
) : BaseViewModel<MainScreenState>() {

    init {
        getProperties()
    }

    private fun getProperties() {
        safeLaunch {
            delay(2000)
            repo.getProperties().onEach {
                it.either(
                    fnLoading = {},
                    fnSuccess = { propList ->
                        updateUiState(
                            UiState.Success(MainScreenState(propertyList = propList ?: emptyList()))
                        )
                    },
                    fnError = {}
                )
            }.launchIn(viewModelScope)
        }
    }

    private fun reverseList() {
        //caution: works only with immutable list, it is not working if it is a mutableList
        val list: MutableList<PropertyUiModel> =
            currentState.successData.propertyList.toMutableList()
        list.add(
            1,
            PropertyUiModel(
                "",
                "Property X",
                "This is dynamically added\nrow, ",
                "https://www.iqiglobal.com/blog/wp-content/uploads/2019/08/Dubai-at-Day-960x655.jpg"
            )
        )
        list.reversed()
        updateUiState(
            UiState.Success(
                currentState.successData.copy(
                    propertyList = list
                )
            )
        )
    }

    fun onRefresh() {
        updateUiState(
            UiState.Success(
                currentState.successData.copy(
                    isSwipeRefreshing = true
                )
            )
        )
        getProperties()
    }

    fun onFabClicked() {
        showToast(message = "from FAB", duration = Toast.LENGTH_LONG)
        reverseList()
    }

    fun onCardClick(name: String) {
        showToast(name)
    }
}
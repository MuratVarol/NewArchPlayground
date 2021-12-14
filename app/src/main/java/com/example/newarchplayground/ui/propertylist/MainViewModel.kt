package com.example.newarchplayground.ui.propertylist

import android.widget.Toast
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import com.example.newarchplayground.ui.common.BaseStateViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.delegate.toast.ToastControllerImpl
import com.example.newarchplayground.ui.delegate.toast.IToastController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


data class MainScreenState(
    val propertyList: List<PropertyUiModel> = emptyList()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase
) : BaseStateViewModel<MainScreenState>(), IToastController by ToastControllerImpl() {

    init {
        getProperties()
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
        updateUiState {
            UiState.Success(
                currentState.successData.copy(
                    propertyList = list
                )
            )
        }
    }

    fun getProperties() {
        safeLaunch {
            delay(2000)
            propertyListUseCase(this) { state ->
                updateUiState { state }
            }
        }
    }

    fun onFabClicked() {
        showToast(message = "from FAB", duration = Toast.LENGTH_LONG)
        reverseList()
    }
}
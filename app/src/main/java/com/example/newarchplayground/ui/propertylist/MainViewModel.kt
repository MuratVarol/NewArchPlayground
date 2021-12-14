package com.example.newarchplayground.ui.propertylist

import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import com.example.newarchplayground.ui.common.BaseSateViewModel
import com.example.newarchplayground.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase
) : BaseSateViewModel<PropertyListContract.State>() {

    init {
        getProperties()
    }

    fun reverseList() {
        //caution: works only with immutable list, it is not working if it is a mutableList
//        val list: MutableList<PropertyUiModel> =
//            currentScreenState.successData.propertyListState.successData?.toMutableList()!!
//        list.removeAt(1)
//        list.add(
//            1,
//            PropertyUiModel(
//                "",
//                "Property X",
//                "This is dynamically added\nrow, ",
//                "https://www.iqiglobal.com/blog/wp-content/uploads/2019/08/Dubai-at-Day-960x655.jpg"
//            )
//        )
//        updateUiSuccessState {
//            it.copy(
//                propertyList = list.reversed()
//            )
//        }
    }

    fun getProperties() {
        safeLaunch {
            propertyListUseCase()
                .onEach { data ->
//                    updateUiSuccessState {
//                        currentScreenState.successData.copy(
//                            propertyListState = data
//                        )
//                    }
                }
                .catch { e -> e.printStackTrace() }
                .launchIn(viewModelScope)
        }
    }
}
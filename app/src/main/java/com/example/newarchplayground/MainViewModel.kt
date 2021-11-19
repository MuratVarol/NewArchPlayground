package com.example.newarchplayground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase
) : ViewModel() {

    // same as LiveData's LiveData and MutableLiveData
    // it is about encapsulating
    // Composable supports StateFlow like Databinding's LiveData
    // we need MutableStateFlow to update ui after service updated list
    private val _list = MutableStateFlow(emptyList<PropertyUiModel>())
    val propertyList: StateFlow<List<PropertyUiModel>> get() = _list

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        getProperties()
    }

    fun reverseList(){
        //caution: works only with immutable list, it is not working if it is a mutableList
        val list : MutableList<PropertyUiModel> = _list.value.toMutableList()
        list.removeAt(1)
        list.add(1,PropertyUiModel("","Property X","This is dynamically added\nrow, ","https://www.iqiglobal.com/blog/wp-content/uploads/2019/08/Dubai-at-Day-960x655.jpg"))
        _list.value = list.reversed()
    }

    fun getProperties(){
        _isLoading.value = true
        propertyListUseCase.getProperties()
            .onEach {
                _list.value = it
                _isLoading.value = false
            }
            .flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }
}
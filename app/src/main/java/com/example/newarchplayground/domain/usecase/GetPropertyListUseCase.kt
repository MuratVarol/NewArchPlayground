package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.usecase.IUseCase
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.delegate.mapper.IUiStateMap
import com.example.newarchplayground.ui.delegate.mapper.UiStateMapImpl
import com.example.newarchplayground.ui.propertylist.MainScreenState
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repo: PropertyRepositoryImp
) : IUseCase<List<PropertyUiModel>?, MainScreenState>, IUiStateMap by UiStateMapImpl() {

    override suspend fun invoke(
        scope: CoroutineScope,
        stateCallback: (UiState<MainScreenState>) -> Unit
    ) {
        repo.getProperties().mapUiState(scope, stateCallback) {
            MainScreenState(propertyList = it ?: emptyList())
        }
    }
}

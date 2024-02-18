package net.mouazkaadan.inshort.presentation.screen.newsPage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.domain.useacase.GetNewsUseCase
import net.mouazkaadan.inshort.utils.Resource
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(NewsScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            getNews(category)
        }
    }

    private var lastScrollIndex = 0
    fun onEvent(event: NewsDetailsScreenEvent) {
        when (event) {
            is NewsDetailsScreenEvent.UpdateScrollPosition -> {
                if (event.position == lastScrollIndex) return
                _uiState.update { state ->
                    state.copy(
                        isScrollUp = event.position > lastScrollIndex
                    )
                }
                lastScrollIndex = event.position
            }
        }
    }

    private fun getNews(category: String) = viewModelScope.launch {
        getNewsUseCase.invoke(category = category).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            categoryName = category,
                            newsItems = result.data,
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false
                        )
                    }
                    _uiEvent.trySend(UIEvent.ShowError(message = result.message))
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowError(val message: String) : UIEvent()
    }
}

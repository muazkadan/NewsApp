package net.mouazkaadan.inshort.presentation.newsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.domain.useacase.GetNewsUseCase
import net.mouazkaadan.inshort.utils.Resource
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState = MutableSharedFlow<NewsUiState>()
    val uiState: SharedFlow<NewsUiState> = _uiState

    fun getNews(category: String) {
        viewModelScope.launch {
            val response = getNewsUseCase.invoke(category = category)
            response.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.emit(NewsUiState(newsItems = result.data.orEmpty()))
                    }
                    is Resource.Error -> {
                        _uiState.emit(NewsUiState(errorMessage = result.message.orEmpty()))
                    }
                    is Resource.Loading -> {
                        _uiState.emit(NewsUiState(isLoading = true))
                    }
                }
            }
        }
    }

    data class NewsUiState(
        val newsItems: List<NewsItem> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}

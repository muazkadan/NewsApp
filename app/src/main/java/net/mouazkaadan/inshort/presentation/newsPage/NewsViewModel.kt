package net.mouazkaadan.inshort.presentation.newsPage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.domain.useacase.GetNewsUseCase
import net.mouazkaadan.inshort.utils.Resource
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(NewsUiState())
        private set

    fun getNews(category: String) {
        viewModelScope.launch {
            val response = getNewsUseCase.invoke(category = category)
            response.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            newsItems = result.data.orEmpty(),
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            newsItems = emptyList(),
                            isLoading = false,
                            errorMessage = result.message.orEmpty()
                        )
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            newsItems = emptyList(),
                            isLoading = true,
                            errorMessage = null
                        )
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

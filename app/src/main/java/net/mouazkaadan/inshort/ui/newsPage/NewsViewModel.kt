package net.mouazkaadan.inshort.ui.newsPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.repository.NewsRepository
import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var _newsResponse = newsRepository.newsData

    val newsResponse: MutableLiveData<NewsResponseModel>
        get() = _newsResponse

    fun getNews(category: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            newsRepository.getNews(category)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
}

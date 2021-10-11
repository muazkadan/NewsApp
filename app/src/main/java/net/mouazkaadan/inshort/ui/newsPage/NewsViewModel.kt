package net.mouazkaadan.inshort.ui.newsPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import net.mouazkaadan.inshort.repository.NewsRepository
import net.mouazkaadan.inshort.ui.newsPage.model.NewsResponseModel
import net.mouazkaadan.inshort.utils.Resource
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var _newsResponse = MutableLiveData<NewsResponseModel>()

    val newsResponse: LiveData<NewsResponseModel>
        get() = _newsResponse

    private var _errorMessage = MutableLiveData<String>()

    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getNews(category: String) {
        viewModelScope.launch {
            when (val response = newsRepository.getNews(category)) {
                is Resource.Error -> {
                    _errorMessage.postValue(response.message!!)
                }
                is Resource.Success -> {
                    _newsResponse.postValue(response.data!!)
                }
            }
        }
    }
}

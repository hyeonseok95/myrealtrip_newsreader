package kr.hs.newsreader.presentation.newslist

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.hs.newsreader.base.BaseViewModel
import kr.hs.newsreader.domain.usecase.LoadGoogleNews
import kr.hs.newsreader.domain.usecase.GetNextArticles
import kr.hs.newsreader.model.SummaryArticle
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val loadGoogleNews: LoadGoogleNews,
    private val getNextArticles: GetNextArticles
) : BaseViewModel() {
    val stateLiveData = MutableLiveData<NewsListModel>()
    val articlesLiveData = MutableLiveData<List<SummaryArticle>>()

    fun requestGoogleNewsArticles() {
        viewModelScope.launch {
            try {
                stateLiveData.postValue(NewsListModel.StartLoading)
                loadGoogleNews()
                requestArticlesNextPage()
            } catch (exception: Exception) {
                //NetworkError
                stateLiveData.postValue(NewsListModel.HideLoading)
            }
        }
    }

    fun refreshArticles() {
        viewModelScope.launch {
            getNextArticles.refresh()
            requestGoogleNewsArticles()
        }
    }

    fun requestArticlesNextPage() {
        stateLiveData.postValue(NewsListModel.StartLoading)
        getNextArticles()
            .subscribe({
                articlesLiveData.postValue(it)
            }, {
                if (it !is RuntimeException) {
                    stateLiveData.postValue(NewsListModel.HideLoading)
                }
            })
            .bind()
    }
}
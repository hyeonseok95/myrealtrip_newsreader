package kr.hs.newsreader.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.hs.newsreader.base.BaseViewModel

class SplashViewModel : BaseViewModel() {
    val stateLiveData = MutableLiveData<SplashModel>()

    init {
        viewModelScope.launch {
            delay(1300)
            stateLiveData.postValue(SplashModel.GotoNewsList)
        }
    }
}
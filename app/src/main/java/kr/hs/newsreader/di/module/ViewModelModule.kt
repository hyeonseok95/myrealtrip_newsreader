package kr.hs.newsreader.di.module

import dagger.multibindings.IntoMap
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import kr.hs.newsreader.di.key.ViewModelKey
import kr.hs.newsreader.domain.usecase.LoadGoogleNews
import kr.hs.newsreader.domain.usecase.GetNextArticles
import kr.hs.newsreader.presentation.newslist.NewsListViewModel
import kr.hs.newsreader.presentation.splash.SplashViewModel

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    fun bindsNewsListViewModel(
        loadGoogleNews: LoadGoogleNews,
        getNextArticles: GetNextArticles
    ): ViewModel {
        return NewsListViewModel(loadGoogleNews, getNextArticles)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindsSplashViewModel(): ViewModel {
        return SplashViewModel()
    }
}
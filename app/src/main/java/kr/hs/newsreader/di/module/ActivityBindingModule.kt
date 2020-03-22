package kr.hs.newsreader.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.newsreader.presentation.newsdetail.NewsDetailActivity
import kr.hs.newsreader.presentation.newslist.NewsListActivity
import kr.hs.newsreader.presentation.splash.SplashActivity

@Module
interface ActivityBindingModule {
    @ContributesAndroidInjector
    fun bindNewsListActivity(): NewsListActivity

    @ContributesAndroidInjector
    fun bindNewsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    fun bindSplashActivity(): SplashActivity
}
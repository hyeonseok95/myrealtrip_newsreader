package kr.hs.newsreader.di.module

import dagger.Module
import dagger.Provides
import kr.hs.newsreader.domain.data.GoogleNewsRss
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideGoogleNewsRss(): GoogleNewsRss {
        return GoogleNewsRss()
    }
}
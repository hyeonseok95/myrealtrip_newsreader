package kr.hs.newsreader.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import kr.hs.newsreader.BaseApplication
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: BaseApplication): Context {
        return application
    }
}
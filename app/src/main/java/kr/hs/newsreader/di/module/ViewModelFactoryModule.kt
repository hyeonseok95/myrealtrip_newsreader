package kr.hs.newsreader.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import kr.hs.newsreader.di.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
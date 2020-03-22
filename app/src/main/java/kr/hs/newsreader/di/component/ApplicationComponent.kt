package kr.hs.newsreader.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kr.hs.newsreader.BaseApplication
import kr.hs.newsreader.di.module.ActivityBindingModule
import kr.hs.newsreader.di.module.ApplicationModule
import kr.hs.newsreader.di.module.DataModule
import kr.hs.newsreader.di.module.ViewModelFactoryModule
import kr.hs.newsreader.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, DataModule::class, ActivityBindingModule::class, ApplicationModule::class, ViewModelModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
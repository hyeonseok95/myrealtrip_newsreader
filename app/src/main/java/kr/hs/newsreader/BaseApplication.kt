package kr.hs.newsreader

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kr.hs.newsreader.di.component.DaggerApplicationComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}
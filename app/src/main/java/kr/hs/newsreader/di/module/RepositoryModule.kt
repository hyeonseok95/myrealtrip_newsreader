package kr.hs.newsreader.di.module

import dagger.Module
import dagger.Provides
import kr.hs.newsreader.domain.data.GoogleNewsRss
import kr.hs.newsreader.domain.repository.GoogleNewsRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGoogleNewsRepository(googleNewsRss: GoogleNewsRss): GoogleNewsRepository {
        return GoogleNewsRepository(googleNewsRss)
    }
}
package kr.hs.newsreader.di.module

import dagger.Module
import dagger.Provides
import kr.hs.newsreader.domain.repository.GoogleNewsRepository
import kr.hs.newsreader.domain.usecase.LoadGoogleNews
import kr.hs.newsreader.domain.usecase.GetNextArticles

@Module
class UseCaseModule {
    @Provides
    fun provideGetGoogleNewsArticles(googleNewsRepository: GoogleNewsRepository): LoadGoogleNews {
        return LoadGoogleNews(googleNewsRepository)
    }

    @Provides
    fun provideListenArticles(googleNewsRepository: GoogleNewsRepository): GetNextArticles {
        return GetNextArticles(googleNewsRepository)
    }
}
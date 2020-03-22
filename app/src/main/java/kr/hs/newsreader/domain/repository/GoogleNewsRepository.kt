package kr.hs.newsreader.domain.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.hs.newsreader.domain.data.GoogleNewsRss
import kr.hs.newsreader.model.SummaryArticle
import javax.inject.Inject

class GoogleNewsRepository @Inject constructor(private val googleNewsRss: GoogleNewsRss) {
    fun getNextArticles(start: Int, offset: Int): Single<List<SummaryArticle>> =
        googleNewsRss.getNextArticles(start, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    suspend fun loadArticles() {
        googleNewsRss.loadArticles()
    }
}
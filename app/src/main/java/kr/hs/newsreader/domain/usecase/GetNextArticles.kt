package kr.hs.newsreader.domain.usecase

import io.reactivex.rxjava3.core.Single
import kr.hs.newsreader.domain.repository.GoogleNewsRepository
import kr.hs.newsreader.model.SummaryArticle
import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class GetNextArticles @Inject constructor(private val googleNewsRepository: GoogleNewsRepository) {
    var start = 0
    private val isNextArticlesLoading = AtomicBoolean(false)

    operator fun invoke(): Single<List<SummaryArticle>> {
        if (isNextArticlesLoading.getAndSet(true)) {
            return Single.error(
                RuntimeException("이미 로드중입니다.")
            )
        }

        return googleNewsRepository
            .getNextArticles(start, OFFSET)
            .doOnSuccess { start += it.size }
            .doFinally { isNextArticlesLoading.set(false) }
    }

    fun refresh() {
        start = 0
    }

    companion object {
        private const val OFFSET = 10
    }
}
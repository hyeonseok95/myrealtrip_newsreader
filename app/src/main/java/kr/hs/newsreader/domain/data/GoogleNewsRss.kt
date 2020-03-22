package kr.hs.newsreader.domain.data

import android.accounts.NetworkErrorException
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kr.hs.newsreader.model.SummaryArticle
import org.jsoup.Jsoup
import javax.inject.Singleton

@Singleton
class GoogleNewsRss {
    private var articleList: List<Article>? = null

    fun getNextArticles(start: Int, offset: Int): Single<List<SummaryArticle>> =
        Single.create<List<Article>> { emitter ->
            if (articleList == null) emitter.onError(NetworkErrorException())
            else {
                articleList?.subList(
                        start,
                        (start + offset - 1).takeIf { it < articleList?.size ?: 0 }
                            ?: (articleList?.size ?: 1 - 1))
                    ?.let { emitter.onSuccess(it) }
            }
        }.map { it.map { article -> article.toSummary() } }

    suspend fun loadArticles() = withContext(Dispatchers.IO) {
        val parser = Parser()
        articleList = parser.getChannel(GOOGLE_NEWS_RSS_URL).articles
    }

    private fun Article.toSummary(): SummaryArticle {
        var imageUrl: String? = null
        var content: String? = null

        runBlocking(Dispatchers.IO) {
            Jsoup.connect(this@toSummary.link).get().getElementsByTag("meta").forEach {
                when (it.attr("property")) {
                    "og:image" -> {
                        imageUrl = it.attr("content")
                    }
                    "og:description" -> {
                        content = it.attr("content")
                    }
                }
            }
        }

        val keywords =
            content?.split(" ")
                ?.groupBy { it }
                ?.map { it.key to it.value.size }
                ?.sortedWith(Comparator { t1, t2 ->
                    if (t1.second < t2.second) {
                        1
                    } else if (t1.second == t2.second && t1.first > t2.first) {
                        1
                    } else {
                        -1
                    }
                })
                ?.let {
                    it.subList(0, it.size.takeIf { size -> size < 3 } ?: 3)
                }
                ?.map { it.first.trim() }
                ?: emptyList()

        return SummaryArticle(imageUrl, this.title, content, keywords, this.link)
    }

    companion object {
        private const val GOOGLE_NEWS_RSS_URL = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    }
}
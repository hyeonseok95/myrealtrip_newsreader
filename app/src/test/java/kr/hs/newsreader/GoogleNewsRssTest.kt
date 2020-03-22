package kr.hs.newsreader

import kotlinx.coroutines.runBlocking
import org.junit.Test

class GoogleNewsRssTest {
    private val googleNewsRss = kr.hs.newsreader.domain.data.GoogleNewsRss()

    @Test
    fun getArticleTest() {
        runBlocking {
            println(googleNewsRss.loadArticles())
        }
    }
}

package kr.hs.newsreader.presentation.newsdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.*
import kotlinx.android.synthetic.main.activity_news_detail.*
import kr.hs.newsreader.base.BaseActivity
import kr.hs.newsreader.databinding.ActivityNewsDetailBinding
import kr.hs.newsreader.model.SummaryArticle

class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding>() {
    override val binding: ActivityNewsDetailBinding by lazy {
        ActivityNewsDetailBinding.inflate(layoutInflater, null, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webview.webChromeClient = webChromeClient
        webview.webViewClient = webViewClient

        webview.loadUrl(intent.extras?.getString(EXTRA_URL))

        val article = intent?.getParcelableExtra<SummaryArticle>(EXTRA_ARTICLE)

        toolbar.title = article?.title ?: ""
        tags.text = article?.keyword?.reduce { acc, s ->
            if (acc.isEmpty()) {
                s
            } else {
                "$acc, $s"
            }
        }
    }

    private val webChromeClient = object : WebChromeClient() {

    }

    private val webViewClient = object : WebViewClient() {
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            finish()
        }
    }

    companion object {
        private const val EXTRA_URL = "extra_url"
        private const val EXTRA_ARTICLE = "extra_article"

        fun newIntent(context: Context, url: String, summaryArticle: SummaryArticle) =
            Intent(context, NewsDetailActivity::class.java)
                .putExtra(EXTRA_URL, url)
                .putExtra(EXTRA_ARTICLE, summaryArticle)
    }
}
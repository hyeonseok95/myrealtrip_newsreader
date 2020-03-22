package kr.hs.newsreader.presentation.newslist

import kr.hs.newsreader.model.SummaryArticle

interface NewsListNavigator {
    fun gotoDetail(url: String, summaryArticle: SummaryArticle)
    fun hideLoading()
}
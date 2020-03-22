package kr.hs.newsreader.presentation.newslist

sealed class NewsListModel {
    object StartLoading : NewsListModel()
    object HideLoading : NewsListModel()
}
package kr.hs.newsreader.presentation.newslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_news_list.*
import kr.hs.newsreader.base.BaseActivity
import kr.hs.newsreader.databinding.ActivityNewsListBinding
import kr.hs.newsreader.model.SummaryArticle
import kr.hs.newsreader.presentation.newsdetail.NewsDetailActivity
import kr.hs.newsreader.presentation.newslist.adapter.NewsListRecyclerViewAdapter

class NewsListActivity : BaseActivity<ActivityNewsListBinding>(), NewsListNavigator {
    private val newsListViewModel: NewsListViewModel by lazy { getViewModel(NewsListViewModel::class) }
    private val newsListRecyclerViewAdapter by lazy { NewsListRecyclerViewAdapter(this) }

    override val binding: ActivityNewsListBinding by lazy {
        ActivityNewsListBinding.inflate(layoutInflater, null, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.newsListViewModel = newsListViewModel
        recyclerview.adapter = newsListRecyclerViewAdapter

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) { //하단 스크롤 불가능
                    getArticles()
                }
            }
        })

        swipe_refresh.setOnRefreshListener {
            newsListRecyclerViewAdapter.clearItems()
            newsListViewModel.refreshArticles()
            swipe_refresh.isRefreshing = false
        }

        refreshSavedArticles()
        registerObserver()
    }

    private fun refreshSavedArticles() {
        newsListViewModel.requestGoogleNewsArticles() //RSS를 새로 저장
    }

    private fun getArticles() {
        newsListViewModel.requestArticlesNextPage()
    }

    private fun registerObserver() {
        newsListViewModel.articlesLiveData.observe {
            newsListRecyclerViewAdapter.addItems(it)
        }

        newsListViewModel.stateLiveData.observe {
            when (it) {
                NewsListModel.StartLoading -> showLoading()
                NewsListModel.HideLoading -> hideLoading()
            }
        }
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun gotoDetail(url: String, summaryArticle: SummaryArticle) {
        startActivity(NewsDetailActivity.newIntent(this, url, summaryArticle))
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, NewsListActivity::class.java)
    }
}
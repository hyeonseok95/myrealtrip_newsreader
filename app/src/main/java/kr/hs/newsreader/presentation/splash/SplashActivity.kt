package kr.hs.newsreader.presentation.splash

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import kr.hs.newsreader.BuildConfig
import kr.hs.newsreader.R
import kr.hs.newsreader.base.BaseActivity
import kr.hs.newsreader.databinding.ActivitySplashBinding
import kr.hs.newsreader.presentation.newslist.NewsListActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashNavigator {
    override val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater, null, true)
    }

    private val splashViewModel: SplashViewModel by lazy { getViewModel(SplashViewModel::class) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerObserver()
        version.text = getString(R.string.spalsh_version).format(BuildConfig.VERSION_NAME)
    }

    override fun gotoNewsList() {
        startActivity(NewsListActivity.newIntent(this))
    }

    private fun registerObserver() {
        splashViewModel.stateLiveData.observe {
            when (it) {
                SplashModel.GotoNewsList -> {
                    gotoNewsList()
                    finish()
                }
            }
        }
    }
}
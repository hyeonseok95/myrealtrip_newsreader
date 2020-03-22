package kr.hs.newsreader.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewDataBinding> : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val binding: VB

    fun <C : ViewModel> getViewModel(modelClass: KClass<C>) =
        ViewModelProvider(this, viewModelFactory).get(modelClass.java)

    fun <T> LiveData<T>.observe(go: (T) -> Unit) {
        this.observe(this@BaseActivity, Observer { go(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }
}
package kr.hs.newsreader.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    protected fun Disposable.bind() {
        disposables.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
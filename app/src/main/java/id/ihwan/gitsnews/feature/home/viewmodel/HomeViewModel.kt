package id.ihwan.gitsnews.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.ihwan.gitsnews.core.network.service.NewsService
import id.ihwan.gitsnews.core.platform.BaseViewModel
import id.ihwan.gitsnews.feature.home.model.News
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val service: NewsService
) : BaseViewModel() {

    val articles = MutableLiveData<List<News.Articles>>()
    val android = MutableLiveData<List<News.Articles>>()
    val design = MutableLiveData<List<News.Articles>>()

    fun requestHeadline() {
        service
            .getTopHeadline("id")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startLoading() }
            .doAfterTerminate { finishLoading() }
            .subscribe(object : SingleObserver<News> {
                override fun onSuccess(data: News) {
                    data.let {
                        articles.postValue(data.articles)
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            }
            )
    }


    fun requestAndroid() {
        service
            .getEverything("android")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startLoading() }
            .doAfterTerminate { finishLoading() }
            .subscribe(object : SingleObserver<News> {
                override fun onSuccess(data: News) {
                    data.let {
                        android.postValue(data.articles)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("Subskreb", d.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("Erorr", e.toString())
                }

            }
            )
    }

    fun requestDesign() {
        service
            .getEverything("design")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startLoading() }
            .doAfterTerminate { finishLoading() }
            .subscribe(object : SingleObserver<News> {
                override fun onSuccess(data: News) {
                    data.let {
                        design.postValue(data.articles)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("Subskreb", d.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("Erorr", e.toString())
                }

            }
            )
    }
}
package com.baharudin.moviestest.data.source.tv

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.baharudin.moviestest.data.model.tvshow.DataTvShow
import com.baharudin.moviestest.data.network.ApiService
import com.baharudin.moviestest.state.TvShowState
import com.baharudin.moviestest.utils.EspressoIdlingResource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchTvDataSource @Inject constructor(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, DataTvShow>(){

    lateinit var liveData: MutableLiveData<TvShowState>
    lateinit var keyword: String

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataTvShow>
    ) {
        apiService.searchTvShow(keyword, 1)
            .map<TvShowState> {
                callback.onResult(it.data.toMutableList(), 1, 2)
                TvShowState.Result(it)
            }
            .onErrorReturn(TvShowState::Error)
            .toFlowable()
            .startWith(TvShowState.Loading).also {
                EspressoIdlingResource.increment()
            }
            .subscribe(liveData::postValue).also {
                EspressoIdlingResource.decrement()
            }
            .let { return@let disposable::add }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataTvShow>) {
        apiService.searchTvShow(keyword, params.key)
            .map<TvShowState> {
                callback.onResult(it.data.toMutableList(), params.key + 1)
                TvShowState.Result(it)
            }
            .onErrorReturn(TvShowState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataTvShow>) {

    }
}
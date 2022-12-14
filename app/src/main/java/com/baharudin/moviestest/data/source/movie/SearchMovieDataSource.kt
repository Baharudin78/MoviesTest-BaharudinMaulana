package com.baharudin.moviestest.data.source.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.data.network.ApiService
import com.baharudin.moviestest.state.MovieState
import com.baharudin.moviestest.utils.EspressoIdlingResource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchMovieDataSource @Inject constructor(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, DataMovie>() {

    lateinit var liveData: MutableLiveData<MovieState>
    lateinit var keyword: String

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataMovie>
    ) {
        apiService.searchMovie(keyword, 1)
            .map<MovieState>{
                callback.onResult(it.data.toMutableList(), 1, 2)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .startWith(MovieState.Loading).also {
                EspressoIdlingResource.increment()
            }
            .subscribe(liveData::postValue).also {
                EspressoIdlingResource.decrement()
            }
            .let { return@let disposable::add }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {
        apiService.searchMovie(keyword, params.key)
            .map<MovieState>{
                callback.onResult(it.data.toMutableList(), params.key + 1)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {

    }
}
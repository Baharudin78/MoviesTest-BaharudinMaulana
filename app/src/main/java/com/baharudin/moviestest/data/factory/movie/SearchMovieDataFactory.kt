package com.baharudin.moviestest.data.factory.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.data.source.movie.SearchMovieDataSource
import com.baharudin.moviestest.state.MovieState
import javax.inject.Inject

class SearchMovieDataFactory @Inject constructor(
    private val movieSearchDataSource: SearchMovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var keyword: String
    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return movieSearchDataSource.also {
            it.keyword = keyword
            it.liveData = liveData
        }
    }
}
package com.baharudin.moviestest.data.factory.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.data.source.movie.UpcomingMovieDataSource
import com.baharudin.moviestest.state.MovieState
import javax.inject.Inject

class UpcomingMovieDataFactory @Inject constructor(
    private val upcomingMovieDataSource: UpcomingMovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return upcomingMovieDataSource.also {
            it.liveData = liveData
        }
    }
}
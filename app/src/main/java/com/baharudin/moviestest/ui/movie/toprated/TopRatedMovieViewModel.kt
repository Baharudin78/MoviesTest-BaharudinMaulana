package com.baharudin.moviestest.ui.movie.toprated

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.data.repository.Repository
import com.baharudin.moviestest.state.MovieState

class TopRatedMovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val data : MutableLiveData<PagedList<DataMovie>> by lazy {
        MutableLiveData<PagedList<DataMovie>>()
    }

    fun getMovie() {
        repository.getAllTopRatedMovie(state, data)
    }
}
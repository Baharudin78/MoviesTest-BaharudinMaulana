package com.baharudin.moviestest.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baharudin.moviestest.data.repository.Repository
import com.baharudin.moviestest.state.TvShowState

class TvShowViewModel @ViewModelInject constructor(
    val repository: Repository
) : ViewModel() {

    val stateAiringToday : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    val stateTopRated : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    val statePopular : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    fun getAiringTodayTvShow() {
        repository.getAiringTodayTvShow(stateAiringToday)
    }

    fun getTopRatedTvShow() {
        repository.getTopRatedTvShow(stateTopRated)
    }

    fun getPopularTvShow() {
        repository.getPopularTvShow(statePopular)
    }
}
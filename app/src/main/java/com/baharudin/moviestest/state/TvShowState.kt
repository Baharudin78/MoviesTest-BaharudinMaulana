package com.baharudin.moviestest.state

import com.baharudin.moviestest.data.model.tvshow.ResponseTvShow

sealed class TvShowState {
    object Loading : TvShowState()
    data class Result(val data : ResponseTvShow) : TvShowState()
    data class Error(val error : Throwable) : TvShowState()
}
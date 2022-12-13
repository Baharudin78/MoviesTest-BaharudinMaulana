package com.baharudin.moviestest.state

import com.baharudin.moviestest.data.model.detailtv.ResponseDetailTv

sealed class DetailTvShowState {
    object Loading : DetailTvShowState()
    data class Result(val data : ResponseDetailTv) : DetailTvShowState()
    data class Error(val error : Throwable) : DetailTvShowState()
}
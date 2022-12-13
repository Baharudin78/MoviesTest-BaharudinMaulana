package com.baharudin.moviestest.state

import com.baharudin.moviestest.data.model.detailmovie.ResponseDetailMovie

sealed class DetailMovieState {
    object Loading : DetailMovieState()
    data class Result(val data : ResponseDetailMovie) : DetailMovieState()
    data class Error(val error : Throwable) : DetailMovieState()
}
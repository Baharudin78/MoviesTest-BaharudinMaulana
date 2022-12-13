package com.baharudin.moviestest.state

import com.baharudin.moviestest.data.model.videos.ResponseVideo

sealed class VideoState {
    object Loading : VideoState()
    data class Result(val data : ResponseVideo) : VideoState()
    data class Error(val error : Throwable) : VideoState()
}
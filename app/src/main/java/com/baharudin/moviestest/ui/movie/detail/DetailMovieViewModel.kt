package com.baharudin.moviestest.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baharudin.moviestest.data.database.model.MovieEntity
import com.baharudin.moviestest.data.repository.Repository
import com.baharudin.moviestest.state.DetailMovieState
import com.baharudin.moviestest.state.VideoState
import kotlinx.coroutines.*

class DetailMovieViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailMovieState> by lazy {
        MutableLiveData<DetailMovieState>()
    }

    val stateFavorite : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val stateVideo : MutableLiveData<VideoState> by lazy {
        MutableLiveData<VideoState>()
    }

    fun getDetailMovie(movieId: Int) {
        repository.getDetailMovie(movieId, state)
    }

    fun getVideos(type : String, id: Int) {
        repository.getVideos(type, id, stateVideo)
    }

    fun addToFavorite(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataMovie(data) }
            if(checkData.await().isEmpty()){
                repository.addDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                repository.deleteDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }

    fun checkFavorite(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataMovie(data) }
            if(checkData.await().isNotEmpty()){
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }
}
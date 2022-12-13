package com.baharudin.moviestest.data.factory.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.baharudin.moviestest.data.model.tvshow.DataTvShow
import com.baharudin.moviestest.data.source.tv.AiringTvDataSource
import com.baharudin.moviestest.state.TvShowState
import javax.inject.Inject

class AiringTvShowDataFactory @Inject constructor(
    private val airingTvDataSource: AiringTvDataSource
) : DataSource.Factory<Int, DataTvShow>(){

    lateinit var liveData: MutableLiveData<TvShowState>

    override fun create(): DataSource<Int, DataTvShow> {
        return airingTvDataSource.also {
            it.liveData = liveData
        }
    }
}
package com.baharudin.moviestest.data.factory.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.baharudin.moviestest.data.model.tvshow.DataTvShow
import com.baharudin.moviestest.data.source.tv.SearchTvDataSource
import com.baharudin.moviestest.state.TvShowState
import javax.inject.Inject

class SearchTvDataFactory @Inject constructor(
    private val tvSearchDataSource: SearchTvDataSource
) : DataSource.Factory<Int, DataTvShow>(){

    lateinit var keyword: String
    lateinit var liveData: MutableLiveData<TvShowState>

    override fun create(): DataSource<Int, DataTvShow> {
        return tvSearchDataSource.also {
            it.keyword = keyword
            it.liveData = liveData
        }
    }
}
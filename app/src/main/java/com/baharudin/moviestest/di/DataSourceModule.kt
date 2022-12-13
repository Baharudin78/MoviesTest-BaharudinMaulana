package com.baharudin.moviestest.di

import com.baharudin.moviestest.data.network.ApiService
import com.baharudin.moviestest.data.source.movie.PopularMovieDataSource
import com.baharudin.moviestest.data.source.movie.SearchMovieDataSource
import com.baharudin.moviestest.data.source.movie.TopRatedMovieDataSource
import com.baharudin.moviestest.data.source.movie.UpcomingMovieDataSource
import com.baharudin.moviestest.data.source.tv.AiringTvDataSource
import com.baharudin.moviestest.data.source.tv.PopularTvDataSource
import com.baharudin.moviestest.data.source.tv.SearchTvDataSource
import com.baharudin.moviestest.data.source.tv.TopRatedTvDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideUpcomingMovieDataSource(
        apiservice: ApiService
    ) : UpcomingMovieDataSource = UpcomingMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideTopRatedDataSource(
        apiservice: ApiService
    ) : TopRatedMovieDataSource = TopRatedMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun providePopularMovieDataSource(
        apiservice: ApiService
    ) : PopularMovieDataSource = PopularMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchMovieDataSource(
        apiservice: ApiService
    ) : SearchMovieDataSource = SearchMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideAiringTvDataSource(
        apiservice: ApiService
    ) : AiringTvDataSource = AiringTvDataSource(apiservice)

    @Provides
    @Singleton
    fun provideTopRatedTvDataSource(
        apiservice: ApiService
    ) : TopRatedTvDataSource = TopRatedTvDataSource(apiservice)

    @Provides
    @Singleton
    fun providePopularTvDataSource(
        apiservice: ApiService
    ) : PopularTvDataSource = PopularTvDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchTvDataSource(
        apiservice: ApiService
    ) : SearchTvDataSource = SearchTvDataSource(apiservice)
}
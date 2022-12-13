package com.baharudin.moviestest.data.factory

import com.baharudin.moviestest.data.factory.movie.PopularMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.SearchMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.TopRatedMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.UpcomingMovieDataFactory
import com.baharudin.moviestest.data.factory.tvshow.AiringTvShowDataFactory
import com.baharudin.moviestest.data.factory.tvshow.PopularTvShowDataFactory
import com.baharudin.moviestest.data.factory.tvshow.SearchTvDataFactory
import com.baharudin.moviestest.data.factory.tvshow.TopRatedTvShowDataFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Factory  @Inject constructor(
    val upcomingMovieDataFactory: UpcomingMovieDataFactory,
    val topRatedMovieDataFactory: TopRatedMovieDataFactory,
    val popularMovieDataFactory: PopularMovieDataFactory,
    val searchMovieDataFactory: SearchMovieDataFactory,
    val airingTvShowDataFactory: AiringTvShowDataFactory,
    val topRatedTvShowDataFactory: TopRatedTvShowDataFactory,
    val popularTvShowDataFactory: PopularTvShowDataFactory,
    val searchTvDataFactory: SearchTvDataFactory
)
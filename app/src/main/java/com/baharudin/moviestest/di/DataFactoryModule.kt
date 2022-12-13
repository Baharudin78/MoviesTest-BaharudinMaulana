package com.baharudin.moviestest.di

import com.baharudin.moviestest.data.factory.Factory
import com.baharudin.moviestest.data.factory.movie.PopularMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.SearchMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.TopRatedMovieDataFactory
import com.baharudin.moviestest.data.factory.movie.UpcomingMovieDataFactory
import com.baharudin.moviestest.data.factory.tvshow.AiringTvShowDataFactory
import com.baharudin.moviestest.data.factory.tvshow.PopularTvShowDataFactory
import com.baharudin.moviestest.data.factory.tvshow.SearchTvDataFactory
import com.baharudin.moviestest.data.factory.tvshow.TopRatedTvShowDataFactory
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
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        upcomingMovieDataFactory: UpcomingMovieDataFactory,
        topRatedMovieDataFactory: TopRatedMovieDataFactory,
        popularMovieDataFactory: PopularMovieDataFactory,
        searchMovieDataFactory: SearchMovieDataFactory,
        airingTvShowDataFactory: AiringTvShowDataFactory,
        topRatedTvShowDataFactory: TopRatedTvShowDataFactory,
        popularTvShowDataFactory: PopularTvShowDataFactory,
        searchTvDataFactory: SearchTvDataFactory,
    ) : Factory = Factory(
        upcomingMovieDataFactory,
        topRatedMovieDataFactory,
        popularMovieDataFactory,
        searchMovieDataFactory,
        airingTvShowDataFactory,
        topRatedTvShowDataFactory,
        popularTvShowDataFactory,
        searchTvDataFactory
    )

    @Provides
    @Singleton
    fun provideUpcomingMovieFactory(
        upcomingMovieDataSource: UpcomingMovieDataSource
    ) : UpcomingMovieDataFactory = UpcomingMovieDataFactory(upcomingMovieDataSource)

    @Provides
    @Singleton
    fun provideTopRatedMovieFactory(
        topRatedMovieDataSource: TopRatedMovieDataSource
    ) : TopRatedMovieDataFactory = TopRatedMovieDataFactory(topRatedMovieDataSource)

    @Provides
    @Singleton
    fun providePopularMovieFactory(
        popularMovieDataSource: PopularMovieDataSource
    ) : PopularMovieDataFactory = PopularMovieDataFactory(popularMovieDataSource)

    @Provides
    @Singleton
    fun provideSearchMovieFactory(
        searchMovieDataSource: SearchMovieDataSource
    ) : SearchMovieDataFactory = SearchMovieDataFactory(searchMovieDataSource)

    @Provides
    @Singleton
    fun provideAiringTvDataFactory(
        airingTvDataSource: AiringTvDataSource
    ) : AiringTvShowDataFactory = AiringTvShowDataFactory(airingTvDataSource)

    @Provides
    @Singleton
    fun provideTopRatedTvDataFactory(
        topRatedTvDataSource: TopRatedTvDataSource
    ) : TopRatedTvShowDataFactory = TopRatedTvShowDataFactory(topRatedTvDataSource)

    @Provides
    @Singleton
    fun providePopularTvDataFactory(
        popularTvDataSource: PopularTvDataSource
    ) : PopularTvShowDataFactory = PopularTvShowDataFactory(popularTvDataSource)

    @Provides
    @Singleton
    fun provideSearchTvFactory(
        searchTvDataSource: SearchTvDataSource
    ) : SearchTvDataFactory = SearchTvDataFactory(searchTvDataSource)


}
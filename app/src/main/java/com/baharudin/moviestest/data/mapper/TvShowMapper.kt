package com.baharudin.moviestest.data.mapper

import com.baharudin.moviestest.data.database.model.TvShowEntity
import com.baharudin.moviestest.data.model.tvshow.DataTvShow

object TvShowMapper {
    fun mapEntityToResponse(data: TvShowEntity) =
        DataTvShow(
            data.backdrop_path,
            data.id, data.overview,
            data.poster_path,
            data.name,
            data.first_air_date,
            data.vote_average,
            data.popularity
        )

    fun mapResponseToEntity(data: DataTvShow) =
        TvShowEntity(
            data.backdrop_path,
            data.id, data.overview,
            data.poster_path,
            data.name,
            data.first_air_date,
            data.vote_average,
            data.popularity
        )
}
package com.mkhairulramadhan.core.utils

import com.mkhairulramadhan.core.data.local.entity.MovieEntity
import com.mkhairulramadhan.core.data.local.entity.TvEntity
import com.mkhairulramadhan.core.data.remote.MovieDataItem
import com.mkhairulramadhan.core.data.remote.TvDataItem
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.domain.model.TvModel

object DataMapper {
    fun mapMovieDataItemToMovieEntities(input: List<MovieDataItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                    id = it.id,
                    title = it.title,
                    backDropImage = it.backdrop_path,
                    posterImage = it.poster_path,
                    year = it.release_date?.dateToYear(),
                    star = it.vote_average.toString(),
                    language = it.original_language,
                    synopsis = it.overview,
                    favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDataItemToMovieEntitiesDetail(input: MovieDataItem): MovieEntity {
        return MovieEntity(
                id = input.id,
                title = input.title,
                backDropImage = input.backdrop_path,
                posterImage = input.poster_path,
                year = input.release_date?.dateToYear(),
                star = input.vote_average.toString(),
                language = input.original_language,
                synopsis = input.overview,
                favorite = false
        )
    }

    fun mapTvDataItemToTvEntities(input: List<TvDataItem>): List<TvEntity> {
        val movieList = ArrayList<TvEntity>()
        input.map {
            val movie = TvEntity(
                    id = it.id,
                    name = it.name,
                    backDropImage = it.backdrop_path,
                    posterImage = it.poster_path,
                    year = it.first_air_date?.dateToYear(),
                    star = it.vote_average.toString(),
                    language = it.original_language,
                    synopsis = it.overview,
                    favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvDataItemToTvEntitiesDetail(input: TvDataItem): TvEntity {
        return TvEntity(
                id = input.id,
                name = input.name,
                backDropImage = input.backdrop_path,
                posterImage = input.poster_path,
                year = input.first_air_date?.dateToYear(),
                star = input.vote_average.toString(),
                language = input.original_language,
                synopsis = input.overview,
                favorite = false
        )
    }

    fun mapMovieEntityToMovieModel(input: List<MovieEntity>): List<MovieModel> {
        val movieList = ArrayList<MovieModel>()
        input.map {
            val movie = MovieModel(
                    id = it.id,
                    title = it.title,
                    backDropImage = it.backDropImage,
                    posterImage = it.posterImage,
                    year = it.year,
                    star = it.star,
                    language = it.language,
                    synopsis = it.synopsis,
                    favorite = it.favorite
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvEntityToTvModel(input: List<TvEntity>): List<TvModel> {
        val tvList = ArrayList<TvModel>()
        input.map {
            val tv = TvModel(
                    id = it.id,
                    name = it.name,
                    backDropImage = it.backDropImage,
                    posterImage = it.posterImage,
                    year = it.year,
                    star = it.star,
                    language = it.language,
                    synopsis = it.synopsis,
                    favorite = it.favorite
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapMovieEntityToMovieDetail(input: MovieEntity): MovieModel {
        return MovieModel(
                id = input.id,
                title = input.title,
                backDropImage = input.backDropImage,
                posterImage = input.posterImage,
                year = input.year,
                star = input.star,
                language = input.language,
                synopsis = input.synopsis,
                favorite = input.favorite
        )
    }

    fun mapTvEntityToTvModelDetail(input: TvEntity): TvModel {
        return TvModel(
                id = input.id,
                name = input.name,
                backDropImage = input.backDropImage,
                posterImage = input.posterImage,
                year = input.year,
                star = input.star,
                language = input.language,
                synopsis = input.synopsis,
                favorite = input.favorite
        )
    }

    fun mapMovieModelToMovieEntity(input: MovieModel): MovieEntity {
        return MovieEntity(
                id = input.id,
                title = input.title,
                backDropImage = input.backDropImage,
                posterImage = input.posterImage,
                year = input.year,
                star = input.star,
                language = input.language,
                synopsis = input.synopsis,
                favorite = input.favorite
        )
    }

    fun mapTvModelToTvEntity(input: TvModel): TvEntity {
        return TvEntity(
                id = input.id,
                name = input.name,
                backDropImage = input.backDropImage,
                posterImage = input.posterImage,
                year = input.year,
                star = input.star,
                language = input.language,
                synopsis = input.synopsis,
                favorite = input.favorite
        )
    }

    private fun String.dateToYear(): String{
        return this.split("-").toTypedArray()[0]
    }


}
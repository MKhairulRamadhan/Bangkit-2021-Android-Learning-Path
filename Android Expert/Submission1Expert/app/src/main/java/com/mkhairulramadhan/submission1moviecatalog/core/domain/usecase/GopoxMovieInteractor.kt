package com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase

import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.MovieModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.TvModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.repository.IGopoxMovieRepository

class GopoxMovieInteractor(private val gopoxRepository: IGopoxMovieRepository): GopoxMovieUseCase {
    override fun getAllMovie() = gopoxRepository.getAllMovie()

    override fun getDetailMovie(id: Int)= gopoxRepository.getDetailMovie(id)

    override fun setMovieFavorite(movie: MovieModel, state: Boolean) = gopoxRepository.setMovieFavorite(movie, state)

    override fun getFavoriteMovie() = gopoxRepository.getFavoriteMovie()

    override fun getAllTv() = gopoxRepository.getAllTv()

    override fun getDetailTv(id: Int) = gopoxRepository.getDetailTv(id)

    override fun setTvFavorite(tv: TvModel, state: Boolean) = gopoxRepository.setTvFavorite(tv, state)

    override fun getFavoriteTv() = gopoxRepository.getFavoriteTv()
}
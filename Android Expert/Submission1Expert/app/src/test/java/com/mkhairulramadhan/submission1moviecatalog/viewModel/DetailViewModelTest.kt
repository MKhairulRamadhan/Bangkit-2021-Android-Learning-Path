package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{

    private lateinit var viewModelDetail: DetailViewModel
    private val dummyMovie = DataDummy.generateDataMovie()[0]
    private val dummyTv = DataDummy.generateDataTv()[0]
    private val idMovie = dummyMovie.id
    private val idTv = dummyTv.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GopoxMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<ResourceData<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<ResourceData<TvEntity>>


    @Before
    fun setUp(){
        viewModelDetail = DetailViewModel(repository)
        viewModelDetail.setSelectMovie(idMovie)
        viewModelDetail.setSelectTv(idTv)

    }

    @Test
    fun getDetailMovie(){
        val resourceData: ResourceData<MovieEntity> = ResourceData.success(dummyMovie)
        val entity = MutableLiveData<ResourceData<MovieEntity>>()
        entity.value = resourceData
        `when`(repository.getDetailMovie(idMovie)).thenReturn(entity)
        viewModelDetail.setSelectMovie(idMovie)
        viewModelDetail.allMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(resourceData)
    }

    @Test
    fun getDetailTv(){
        val resourceData: ResourceData<TvEntity> = ResourceData.success(dummyTv)
        val entity = MutableLiveData<ResourceData<TvEntity>>()
        entity.value = resourceData
        `when`(repository.getDetailTv(idTv)).thenReturn(entity)
        viewModelDetail.setSelectTv(idTv)
        viewModelDetail.allTv.observeForever(tvObserver)
        verify(tvObserver).onChanged(resourceData)
    }

    @Test
    fun setfavoriteMovie() {
        val state = !dummyMovie.favorite
        val dummy = ResourceData.success(dummyMovie)
        val movie = MutableLiveData<ResourceData<MovieEntity>>()
        movie.value = dummy
        `when`(repository.getDetailMovie(idMovie)).thenReturn(movie)
        Mockito.doNothing().`when`(repository).setMovieFavorite(dummyMovie, state)
        viewModelDetail.allMovie.observeForever(movieObserver)
        viewModelDetail.setMovieFavorire()
        Mockito.verify(repository, Mockito.times(1)).setMovieFavorite(dummyMovie, state)
    }

    @Test
    fun setfavoriteTv() {
        val state = !dummyTv.favorite
        val dummy = ResourceData.success(dummyTv)
        val tv = MutableLiveData<ResourceData<TvEntity>>()
        tv.value = dummy
        `when`(repository.getDetailTv(idTv)).thenReturn(tv)
        Mockito.doNothing().`when`(repository).setTvFavorite(dummyTv, state)
        viewModelDetail.allTv.observeForever(tvObserver)
        viewModelDetail.setTvFavorire()
        Mockito.verify(repository, Mockito.times(1)).setTvFavorite(dummyTv, state)
    }
}
package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{
    private lateinit var viewModelFavorite: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GopoxMovieRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModelFavorite = FavoriteViewModel(repository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummy = pagedListMovie
        Mockito.`when`(dummy.size).thenReturn(20)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummy
        Mockito.`when`(repository.getFavoriteMovie()).thenReturn(movie)
        val entity = viewModelFavorite.getFavoriteMovie().value
        Mockito.verify(repository).getFavoriteMovie()
        assertNotNull(entity)
        assertEquals(20, entity?.size)
        viewModelFavorite.getFavoriteMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummy)
    }

    @Test
    fun getFavoriteTv() {
        val dummy = pagedListTv
        Mockito.`when`(dummy.size).thenReturn(20)
        val movie = MutableLiveData<PagedList<TvEntity>>()
        movie.value = dummy
        Mockito.`when`(repository.getFavoriteTv()).thenReturn(movie)
        val entity = viewModelFavorite.getFavoriteTv().value
        Mockito.verify(repository).getFavoriteTv()
        assertNotNull(entity)
        assertEquals(20, entity?.size)
        viewModelFavorite.getFavoriteTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummy)
    }

}
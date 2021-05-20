package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieTvViewModelTest{

    private lateinit var viewModel: MovieTvViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GopoxMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<ResourceData<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvObserver: Observer<ResourceData<PagedList<TvEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setUp(){
        viewModel = MovieTvViewModel(repository)
    }

    @Test
    fun getMovieData(){
        val dataMovie = ResourceData.success(moviePagedList)
        `when`(dataMovie.data?.size).thenReturn(20)
        val movie = MutableLiveData<ResourceData<PagedList<MovieEntity>>>()
        movie.value = dataMovie

        `when` (repository.getAllMovie()).thenReturn(movie)
        val movieList = viewModel.getMovieData().value?.data
        verify(repository).getAllMovie()

        assertNotNull(movieList)
        assertEquals(20, movieList?.size)

        viewModel.getMovieData().observeForever(movieObserver)
        verify(movieObserver).onChanged(dataMovie)
    }

    @Test
    fun getTvData(){
        val dataTv = ResourceData.success(tvPagedList)
        `when`(dataTv.data?.size).thenReturn(20)
        val tv = MutableLiveData<ResourceData<PagedList<TvEntity>>>()
        tv.value = dataTv

        `when` (repository.getAllTv()).thenReturn(tv)
        val tvList = viewModel.getTvData().value?.data
        verify(repository).getAllTv()

        assertNotNull(tvList)
        assertEquals(20, tvList?.size)

        viewModel.getTvData().observeForever(tvObserver)
        verify(tvObserver).onChanged(dataTv)
    }

}
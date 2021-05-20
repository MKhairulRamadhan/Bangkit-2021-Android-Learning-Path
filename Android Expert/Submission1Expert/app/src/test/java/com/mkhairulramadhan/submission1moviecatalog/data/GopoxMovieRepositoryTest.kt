package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mkhairulramadhan.submission1moviecatalog.data.local.LocalDataSource
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import com.mkhairulramadhan.submission1moviecatalog.utils.ExecutorApp
import com.mkhairulramadhan.submission1moviecatalog.utils.LiveDataTesting
import com.mkhairulramadhan.submission1moviecatalog.utils.PagedListTesting
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class GopoxMovieRepositoryTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localSource = mock(LocalDataSource::class.java)
    private val executorApp = mock(ExecutorApp::class.java)
    private val remoteSource = mock(RemoteDataSource::class.java)
    private val repos = FakeGopoxMovieRepository(remoteSource,localSource,executorApp)
    private val movieDummy = DataDummy.generateDataMovie()
    private val tvDummy = DataDummy.generateDataTv()
    private val idMovie = movieDummy[0].id
    private val idTv = tvDummy[0].id

    @Test
    fun getAllMovie(){
        val sourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localSource.getAllMovie()).thenReturn(sourceFactory)
        repos.getAllMovie()
        val entity = ResourceData.success(PagedListTesting.testPagedList(DataDummy.generateDataMovie()))
        verify(localSource).getAllMovie()
        assertNotNull(entity.data)
        assertEquals(movieDummy.size, entity.data?.size)
    }

    @Test
    fun getAllTv(){
        val sourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(localSource.getAllTv()).thenReturn(sourceFactory)
        repos.getAllTv()
        val entity = ResourceData.success(PagedListTesting.testPagedList(DataDummy.generateDataTv()))
        verify(localSource).getAllTv()
        assertNotNull(entity.data)
        assertEquals(tvDummy.size, entity.data?.size)
    }

    @Test
    fun getDetailMovie(){
        val movieData = MutableLiveData<MovieEntity>()
        val dataDummy = movieDummy[0]
        movieData.value = dataDummy
        `when`(localSource.getDetailMovie(idMovie)).thenReturn(movieData)
        val movieDetail = LiveDataTesting.getValue(repos.getDetailMovie(idMovie))
        verify(localSource).getDetailMovie(idMovie)
        assertNotNull(movieDetail)
        assertNotNull(movieDetail.data?.id)
        assertNotNull(movieDetail.data?.title)
        assertNotNull(movieDetail.data?.backDropImage)
        assertNotNull(movieDetail.data?.posterImage)
        assertNotNull(movieDetail.data?.year)
        assertNotNull(movieDetail.data?.star)
        assertNotNull(movieDetail.data?.language)
        assertNotNull(movieDetail.data?.synopsis)
        assertNotNull(movieDetail.data?.favorite)
        assertEquals(movieDummy[0].id, movieDetail.data?.id)
        assertEquals(movieDummy[0].title, movieDetail.data?.title)
        assertEquals(movieDummy[0].backDropImage, movieDetail.data?.backDropImage)
        assertEquals(movieDummy[0].posterImage, movieDetail.data?.posterImage)
        assertEquals(movieDummy[0].year, movieDetail.data?.year)
        assertEquals(movieDummy[0].star, movieDetail.data?.star)
        assertEquals(movieDummy[0].language, movieDetail.data?.language)
        assertEquals(movieDummy[0].synopsis, movieDetail.data?.synopsis)
        assertEquals(movieDummy[0].favorite, movieDetail.data?.favorite)
    }

    @Test
    fun getDetailTv(){
        val tvData = MutableLiveData<TvEntity>()
        val dataDummy = tvDummy[0]
        tvData.value = dataDummy
        `when`(localSource.getDetailTv(idTv)).thenReturn(tvData)
        val tvDetail = LiveDataTesting.getValue(repos.getDetailTv(idTv))
        verify(localSource).getDetailTv(idTv)
        assertNotNull(tvDetail)
        assertNotNull(tvDetail.data?.id)
        assertNotNull(tvDetail.data?.name)
        assertNotNull(tvDetail.data?.backDropImage)
        assertNotNull(tvDetail.data?.posterImage)
        assertNotNull(tvDetail.data?.year)
        assertNotNull(tvDetail.data?.star)
        assertNotNull(tvDetail.data?.language)
        assertNotNull(tvDetail.data?.synopsis)
        assertNotNull(tvDetail.data?.favorite)
        assertEquals(tvDummy[0].id, tvDetail.data?.id)
        assertEquals(tvDummy[0].name, tvDetail.data?.name)
        assertEquals(tvDummy[0].backDropImage, tvDetail.data?.backDropImage)
        assertEquals(tvDummy[0].posterImage, tvDetail.data?.posterImage)
        assertEquals(tvDummy[0].year, tvDetail.data?.year)
        assertEquals(tvDummy[0].star, tvDetail.data?.star)
        assertEquals(tvDummy[0].language, tvDetail.data?.language)
        assertEquals(tvDummy[0].synopsis, tvDetail.data?.synopsis)
        assertEquals(tvDummy[0].favorite, tvDetail.data?.favorite)
    }

    @Test
    fun getFavoriteMovie(){
        val sourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localSource.getFavoriteMovie()).thenReturn(sourceFactory)
        repos.getFavoriteMovie()
        val entity = ResourceData.success(PagedListTesting.testPagedList(DataDummy.generateDataMovie()))
        verify(localSource).getFavoriteMovie()
        assertNotNull(entity)
        assertEquals(movieDummy.size, entity.data?.size)
    }

    @Test
    fun getFavoriteTv(){
        val sourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(localSource.getFavoriteTv()).thenReturn(sourceFactory)
        repos.getFavoriteTv()
        val entity = ResourceData.success(PagedListTesting.testPagedList(DataDummy.generateDataTv()))
        verify(localSource).getFavoriteTv()
        assertNotNull(entity)
        assertEquals(movieDummy.size, entity.data?.size)
    }

}
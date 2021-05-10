package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import com.mkhairulramadhan.submission1moviecatalog.utils.LiveDataTesting
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class GopoxMovieRepositoryTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)

    private val repo = FakeGopoxMovieRepository(remote)
    private val dummyMovie = DataDummy.generateDataMovieResponse()
    private val movieId = dummyMovie[0].id
    private val dummyTv = DataDummy.generateDataTvResponse()
    private val tvId = dummyTv[0].id
    private val movieResponse = DataDummy.generateDataMovieResponse()[0]
    private val tvResponse = DataDummy.generateDataTvResponse()[0]

    @Test
    fun getAllMovie(){
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.CustomGetAllMovieCallback).onResponse(dummyMovie)
                null
            }.`when`(remote).getAllMovie(any())
        }

        val movieData = LiveDataTesting.getValue(repo.getAllMovie())

        runBlocking {
            verify(remote).getAllMovie(any())
        }

        assertNotNull(movieData)
        assertEquals(dummyMovie.size, movieData.size)
    }

    @Test
    fun getAllTv(){
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.CustomGetAllTvCallback).onResponse(dummyTv)
                null
            }.`when`(remote).getAllTv(any())
        }

        val tvData = LiveDataTesting.getValue(repo.getAllTv())

        runBlocking {
            verify(remote).getAllTv(any())
        }

        assertNotNull(tvData)
        assertEquals(dummyMovie.size, tvData.size)
    }

    @Test
    fun getDetailMovie(){
        runBlocking {
            doAnswer { invocation -> (invocation.arguments[1] as RemoteDataSource.CustomDetailMovieCallback).onResponse(movieResponse)
                null
            }.`when`(remote).getDetailMovie(eq(movieId), any())
        }

        val dataMovie = LiveDataTesting.getValue(repo.getDetailMovie(movieId))

        runBlocking {
            verify(remote).getDetailMovie(eq(movieId), any())
        }

        assertNotNull(dataMovie)
        assertEquals(movieResponse.id, dataMovie.id)
    }

    @Test
    fun getDetailTv(){
        runBlocking {
            doAnswer { invocation -> (invocation.arguments[1] as RemoteDataSource.CustomDetailTvCallback).onResponse(tvResponse)
                null
            }.`when`(remote).getDetailTv(eq(tvId), any())
        }

        val dataTv = LiveDataTesting.getValue(repo.getDetailTv(tvId))

        runBlocking {
            verify(remote).getDetailTv(eq(tvId), any())
        }

        assertNotNull(dataTv)
        assertEquals(tvResponse.id, dataTv.id)
    }

}
package com.lukitor.projectandroidjetpackpro1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import com.lukitor.projectandroidjetpackpro1.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)
    private val allDataResponses = DataDummy.generateRemoteAllData()
    private val movieResponses = DataDummy.generateRemoteMovieData()
    private val tvResponses = DataDummy.generateRemoteTVData()
    private val judulResponse = DataDummy.generateRemoteAllData()

    @Test
    fun getAllList() {
        val dummyCourses = MutableLiveData<List<MovieEntitiy>>()
        dummyCourses.value = DataDummy.generateAllData()
        Mockito.`when`(local.getAllData()).thenReturn(dummyCourses)
        val courseEntities = LiveDataTestUtil.getValue(movieRepository.getAll())
        verify(local).getAllData()
        Assert.assertNotNull(courseEntities)
        assertEquals(allDataResponses.size.toLong(), courseEntities.size.toLong())
    }
    @Test
    fun getMovieList() {
        val dummyCourses = MutableLiveData<List<MovieEntitiy>>()
        dummyCourses.value = DataDummy.generateMovieData()
        Mockito.`when`(local.getAllMovie()).thenReturn(dummyCourses)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovie())
        verify(local).getAllMovie()
        Assert.assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getTVList() {
        val dummyCourses = MutableLiveData<List<MovieEntitiy>>()
        dummyCourses.value = DataDummy.generateTVData()
        Mockito.`when`(local.getAllTV()).thenReturn(dummyCourses)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getTV())
        verify(local).getAllTV()
        Assert.assertNotNull(movieEntities)
        assertEquals(tvResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetail() {
        val dummyCourses = MutableLiveData<List<MovieEntitiy>>()
        dummyCourses.value = DataDummy.generateAllData()
        Mockito.`when`(local.getAllData()).thenReturn(dummyCourses)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAll())
        verify(local).getAllData()
        Assert.assertNotNull(movieEntities)
        assertEquals(judulResponse.get(0).judul, movieEntities[0].judul)
        assertEquals(judulResponse.get(0).image, movieEntities[0].image)
        assertEquals(judulResponse.get(0).description, movieEntities[0].description)
        assertEquals(judulResponse.get(0).rating, movieEntities[0].rating)
        assertEquals(judulResponse.get(0).genre, movieEntities[0].genre)
        assertEquals(judulResponse.get(0).type, movieEntities[0].type)
    }
}
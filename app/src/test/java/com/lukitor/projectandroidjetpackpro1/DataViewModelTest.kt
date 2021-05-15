package com.lukitor.projectandroidjetpackpro1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataViewModelTest {
    private lateinit var viewModel: DataViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var movieRepository: MovieRepository
    @Mock
    private lateinit var observer: Observer<List<MovieEntitiy>>
    @Before
    fun setUp() {viewModel = DataViewModel(movieRepository)}

    @Test
    fun getAllList() {
        val dummyMovie = DataDummy.generateAllData()
        val movie = MutableLiveData<List<MovieEntitiy>>()
        movie.value = dummyMovie
        Mockito.`when`(movieRepository.getAll()).thenReturn(movie)
        val movieEntity = viewModel.getAll().value
        Mockito.verify(movieRepository).getAll()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)
        viewModel.getAll().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getMovieList() {
        val dummyMovie = DataDummy.generateMovieData()
        val movie = MutableLiveData<List<MovieEntitiy>>()
        movie.value = dummyMovie
        Mockito.`when`(movieRepository.getMovie()).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value
        Mockito.verify(movieRepository).getMovie()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(10, movieEntity?.size)
        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowList() {
        val dummyMovie = DataDummy.generateTVData()
        val movie = MutableLiveData<List<MovieEntitiy>>()
        movie.value = dummyMovie
        Mockito.`when`(movieRepository.getTV()).thenReturn(movie)
        val movieEntity = viewModel.getTV().value
        Mockito.verify(movieRepository).getTV()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(10, movieEntity?.size)
        viewModel.getTV().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}
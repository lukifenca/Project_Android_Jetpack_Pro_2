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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: MovieRepository

    @Mock
    private lateinit var observer : Observer<List<MovieEntitiy>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(academyRepository)
    }

    @Test
    fun getFavorite() {
        val dummyCourses = DataDummy.generateAllData()
        val courses = MutableLiveData<List<MovieEntitiy>>()
        courses.value = dummyCourses

        Mockito.`when`(academyRepository.getFavorite("Movie")).thenReturn(courses)
        val courseEntities = viewModel.getBookmarks("Movie").value
        Mockito.verify(academyRepository).getFavorite("Movie")
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(20, courseEntities?.size)

        viewModel.getBookmarks("Movie").observeForever(observer)
        Mockito.verify(observer).onChanged(dummyCourses)
    }
}
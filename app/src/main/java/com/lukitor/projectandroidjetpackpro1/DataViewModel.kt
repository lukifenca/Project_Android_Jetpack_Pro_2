package com.lukitor.projectandroidjetpackpro1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.vo.Resource

class DataViewModel(private val movieRepository: MovieRepository) : ViewModel(){
    fun getAllData(): LiveData<Resource<List<MovieEntitiy>>> = movieRepository.getAllData()
    fun getAll(): LiveData<List<MovieEntitiy>> = movieRepository.getAll()
    fun getMovie(): LiveData<List<MovieEntitiy>> = movieRepository.getMovie()
    fun getTV(): LiveData<List<MovieEntitiy>> = movieRepository.getTV()
}
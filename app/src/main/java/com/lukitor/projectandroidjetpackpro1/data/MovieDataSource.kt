package com.lukitor.projectandroidjetpackpro1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.vo.Resource

interface MovieDataSource {
    fun getAllData(): LiveData<Resource<List<MovieEntitiy>>>
    fun getAll() : LiveData<List<MovieEntitiy>>
    fun getMovie(): LiveData<List<MovieEntitiy>>
    fun getTV(): LiveData<List<MovieEntitiy>>
    fun getDetail(judul: String): LiveData<List<MovieEntitiy>>
    fun getFavorite(type: String): LiveData<PagedList<MovieEntitiy>>
    fun setFavorite(movie: MovieEntitiy, status: Int)
}
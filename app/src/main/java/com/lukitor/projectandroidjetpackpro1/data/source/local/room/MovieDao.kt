package com.lukitor.projectandroidjetpackpro1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import androidx.paging.DataSource

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieEntity")
    fun getData(): LiveData<List<MovieEntitiy>>
    @Query("SELECT * FROM movieEntity where type = 'Movie'")
    fun getMovie(): LiveData<List<MovieEntitiy>>
    @Query("SELECT * FROM movieEntity where type = 'TV Show'")
    fun getTV(): LiveData<List<MovieEntitiy>>
    @Query("SELECT * FROM movieEntity where id = :id")
    fun getDetail(id: String): LiveData<List<MovieEntitiy>>
    @Query("SELECT * FROM movieEntity where favorite = 1")
    fun getAllFavorite(): DataSource.Factory<Int, MovieEntitiy>
    @Query("SELECT * FROM movieEntity where favorite = 1 and type = :type")
    fun getFavorite(type: String): DataSource.Factory<Int, MovieEntitiy>
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(courses: List<MovieEntitiy>)
    @Update
    fun updateFavorite(movie: MovieEntitiy)
}

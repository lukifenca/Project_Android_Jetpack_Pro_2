package com.lukitor.projectandroidjetpackpro1.data

import androidx.lifecycle.LiveData
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.ApiResponse
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse
import com.lukitor.projectandroidjetpackpro1.vo.Resource
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import java.util.ArrayList

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : MovieDataSource {
    override fun getAllData(): LiveData<Resource<List<MovieEntitiy>>> {
        return object : NetworkBoundResource<List<MovieEntitiy>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntitiy>> = localDataSource.getAllData()
            override fun shouldFetch(data: List<MovieEntitiy>?): Boolean = data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllData()
            public override fun saveCallResult(courseResponses: List<MovieResponse>) {
                val courseList = ArrayList<MovieEntitiy>()
                for (response in courseResponses) {
                    val course = MovieEntitiy(response.id,response.judul,response.image,response.description,response.rating,response.genre,response.type)
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }
    override fun getAll(): LiveData<List<MovieEntitiy>>  = localDataSource.getAllData()
    override fun getMovie(): LiveData<List<MovieEntitiy>> = localDataSource.getAllMovie()
    override fun getTV(): LiveData<List<MovieEntitiy>> = localDataSource.getAllTV()
    override fun getDetail(judul: String): LiveData<List<MovieEntitiy>> = localDataSource.getDetail(judul)
    override fun getFavorite(type: String): LiveData<List<MovieEntitiy>> {
        var temp: LiveData<List<MovieEntitiy>>
        if (type == "" || type == "All") temp = localDataSource.getAllFavorite()
        else temp = localDataSource.getFavorite(type)
        return temp
    }
    override fun setFavorite(movie: MovieEntitiy, status: Int) = appExecutors.diskIO().execute { localDataSource.setFavorite(movie, status)}
}
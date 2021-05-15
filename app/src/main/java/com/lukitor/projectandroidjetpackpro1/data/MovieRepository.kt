package com.lukitor.projectandroidjetpackpro1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.ApiResponse
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import com.lukitor.projectandroidjetpackpro1.vo.Resource

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource,private val appExecutors: AppExecutors) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllData(): LiveData<Resource<List<MovieEntitiy>>> {
        return object : NetworkBoundResource<List<MovieEntitiy>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntitiy>> =
                localDataSource.getAllData()
            override fun shouldFetch(data: List<MovieEntitiy>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllData()
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
    override fun getMovie(): LiveData<List<MovieEntitiy>>  = localDataSource.getAllMovie()
    override fun getTV(): LiveData<List<MovieEntitiy>>  = localDataSource.getAllTV()
    override fun getDetail(judul: String): LiveData<List<MovieEntitiy>> = localDataSource.getDetail(judul)
    override fun getFavorite(type: String): LiveData<PagedList<MovieEntitiy>> {
        var temp: LiveData<PagedList<MovieEntitiy>>
        if (type == "" || type == "All") {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
            temp =  LivePagedListBuilder(localDataSource.getAllFavorite(), config).build()
        }
        else{
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
            temp =  LivePagedListBuilder(localDataSource.getFavorite(type), config).build()
        }
        return temp
    }
    override fun setFavorite(movie: MovieEntitiy, status: Int) = appExecutors.diskIO().execute { localDataSource.setFavorite(movie, status)}
}
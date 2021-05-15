package com.lukitor.projectandroidjetpackpro1.`class`

import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse

object DataDummy {
    fun generateAllData(): ArrayList<MovieEntitiy>{
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            var datamodel = MovieEntitiy(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
            listData.add(datamodel)
        }
        return listData
    }
    fun generateRemoteAllData(): ArrayList<MovieResponse>{
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            var datamodel = MovieResponse(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
            listData.add(datamodel)
        }
        return listData
    }
    fun generateMovieData(): ArrayList<MovieEntitiy>{
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "Movie"){
                var datamodel = MovieEntitiy(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
                listData.add(datamodel)
            }
        }
        return listData
    }
    fun generateRemoteMovieData(): ArrayList<MovieResponse>{
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "Movie"){
                var datamodel = MovieResponse(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
                listData.add(datamodel)
            }
        }
        return listData
    }
    fun generateTVData(): ArrayList<MovieEntitiy>{val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "TV Show"){
                var datamodel = MovieEntitiy(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
                listData.add(datamodel)
            }
        }
        return listData
    }
    fun generateRemoteTVData(): ArrayList<MovieResponse>{
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "TV Show"){
                var datamodel = MovieResponse(id[index],s,image[index],description[index],rating[index],genre[index],type[index])
                listData.add(datamodel)
            }
        }
        return listData
    }
}
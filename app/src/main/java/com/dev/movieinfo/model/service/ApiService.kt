package com.dev.movieinfo.model.service

import com.dev.movieinfo.model.local.MoviesModel
import com.dev.movieinfo.model.remote.GenresResponseApiModel
import com.dev.movieinfo.model.remote.MoviesResponseApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Config.NOW_PLAYING)
    suspend fun getMovies(@Query("page") page:Int,@Query("language") language:String="es-Mx"):Response<MoviesResponseApiModel>

    @GET(Config.GENRE)
    suspend fun getGenres():Response<GenresResponseApiModel>

    @GET(Config.DETAILS)
    suspend fun getMovieDetail(@Path("id") movieId:Int,@Query("language") language:String="es-Mx"):Response<MoviesModel>
}
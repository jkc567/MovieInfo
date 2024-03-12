package com.dev.movieinfo.model.repository.movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dev.movieinfo.model.local.MoviesModel

interface MoviesRepository {
    fun getMoviesPagin():LiveData<PagingData<MoviesModel>>
    suspend fun getMovieDetail(movieId:Int):MoviesModel?
}
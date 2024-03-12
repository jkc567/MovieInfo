package com.dev.movieinfo.model.repository.movies

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dev.movieinfo.model.local.MoviesModel
import com.dev.movieinfo.model.service.Service
import com.dev.movieinfo.utils.Constants
import com.dev.movieinfo.utils.GsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl():MoviesRepository {
    val service=Service.Instance().getApiService()
    override fun getMoviesPagin(): LiveData<PagingData<MoviesModel>> {
        return Pager(PagingConfig(pageSize = 20, prefetchDistance = 2, initialLoadSize = 20)){
            MoviesPaginSource(service)
        }.liveData
    }
     override suspend fun getMovieDetail(movieId:Int):MoviesModel?
    {
        return withContext(Dispatchers.IO){
            val response=service.getMovieDetail(movieId)
            if(response.isSuccessful)
            {
               return@withContext response.body()
            }
            else
            {
                return@withContext null
            }
        }
    }
}
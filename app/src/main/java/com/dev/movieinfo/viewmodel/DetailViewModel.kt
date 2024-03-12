package com.dev.movieinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.movieinfo.model.local.MoviesModel
import com.dev.movieinfo.model.repository.movies.MoviesRepositoryImpl
import kotlinx.coroutines.launch

class DetailViewModel:BaseViewModel() {
    private val moviesRepository= MoviesRepositoryImpl()
     val movieDetail=MutableLiveData<MoviesModel>()
    fun getMovieDetail(movieId:Int)
    {
        viewModelScope.launch {
            try {
                _failure.postValue(false)
                _onSuccess.postValue(false)
                _loading.postValue(true)
                val movie=moviesRepository.getMovieDetail(movieId)
                movie?.let {
                    movieDetail.postValue(it)
                }
                _loading.postValue(false)
                _onSuccess.postValue(true)
            }
            catch (ex:Exception)
            {
                _loading.postValue(false)
                _error.postValue(ex.message)
                _failure.postValue(true)
            }

        }
    }
}
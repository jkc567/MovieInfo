package com.dev.movieinfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev.movieinfo.model.local.MoviesModel
import com.dev.movieinfo.model.repository.movies.MoviesRepository
import com.dev.movieinfo.model.repository.movies.MoviesRepositoryImpl
import kotlinx.coroutines.launch

class MoviesViewModel():BaseViewModel() {
    private val moviesRepository=MoviesRepositoryImpl()
    lateinit var moviePagin:LiveData<PagingData<MoviesModel>>
    init {
        moviePagin=moviesRepository.getMoviesPagin().cachedIn(viewModelScope)
    }

}
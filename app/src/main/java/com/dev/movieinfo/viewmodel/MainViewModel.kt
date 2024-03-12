package com.dev.movieinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dev.movieinfo.model.repository.LoginRepositoryImpl

class MainViewModel:BaseViewModel() {
    val loginRepository= LoginRepositoryImpl()
    fun logOut():Boolean
    {
        return loginRepository.logOut()
    }
}
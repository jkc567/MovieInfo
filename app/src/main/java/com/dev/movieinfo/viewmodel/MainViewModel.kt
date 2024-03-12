package com.dev.movieinfo.viewmodel

import com.dev.movieinfo.model.repository.login.LoginRepositoryImpl

class MainViewModel:BaseViewModel() {
    val loginRepository= LoginRepositoryImpl()
    fun logOut():Boolean
    {
        return loginRepository.logOut()
    }
}
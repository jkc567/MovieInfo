package com.dev.movieinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


 open class BaseViewModel:ViewModel() {
    protected val _error= MutableLiveData<String>()
    val error get() = _error
    protected val _onSuccess= MutableLiveData<Boolean>()
    val onSuccess get() =_onSuccess
    protected val _failure= MutableLiveData<Boolean>()
    val failure get() = _failure
    protected  val _loading= MutableLiveData<Boolean>()
     val loading get() = _loading
}
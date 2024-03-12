package com.dev.movieinfo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.movieinfo.model.repository.login.LoginRepositoryImpl
import com.dev.movieinfo.utils.EmailUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:BaseViewModel() {
    val isValidEmail=MutableLiveData<Boolean>()
    val isValidPassword=MutableLiveData<Boolean>()
    val email=MutableLiveData("")
    val password=MutableLiveData("")
    private val loginRepository= LoginRepositoryImpl()
    fun validateData():Boolean
    {
        isValidEmail.value=email.value!!.trim().isNotEmpty() && EmailUtils.isValidEmail(email.value!!)
        isValidPassword.value=password.value!!.trim().isNotEmpty()
        return isValidEmail.value!! && isValidPassword.value!!
    }
    fun login()
    {
        if(!validateData())return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _failure.postValue(false)
                _loading.postValue(true)
                _onSuccess.postValue(false)
                val result=loginRepository.login(email.value!!,password.value!!)
                Log.d("Movie info",result.user!!.email!!)
                _onSuccess.postValue(true)
                _loading.postValue(false)
            }
            catch (ex:Exception)
            {
                _loading.postValue(false)
                _failure.postValue(true)
                Log.d("Movie info",ex.message!!)
            }
        }
    }
}
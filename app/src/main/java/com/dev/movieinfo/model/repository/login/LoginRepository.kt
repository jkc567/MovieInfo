package com.dev.movieinfo.model.repository.login

import com.google.firebase.auth.AuthResult

interface LoginRepository {
  suspend fun login(email:String,password:String): AuthResult
  fun logOut():Boolean
}
package com.dev.movieinfo.model.repository

import com.google.firebase.auth.AuthResult

interface LoginRepository {
  suspend fun login(email:String,password:String): AuthResult
  fun logOut():Boolean
}
package com.dev.movieinfo.model.repository.login

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl(val aut:FirebaseAuth =FirebaseAuth.getInstance()): LoginRepository {
    override suspend fun login(email:String,password:String):AuthResult {
          return  aut.signInWithEmailAndPassword(email, password).await()
    }

    override fun logOut(): Boolean {
        aut.signOut()
        return true;
    }
}
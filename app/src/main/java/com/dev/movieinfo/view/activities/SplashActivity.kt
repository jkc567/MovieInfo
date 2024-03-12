package com.dev.movieinfo.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth


class SplashActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen: SplashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
    }

    override fun onResume() {
        super.onResume()
        verifyUser()
    }
    private fun verifyUser()
    {
        val user=FirebaseAuth.getInstance().currentUser
        val intent:Intent
        if(user!=null)
        {
            Log.d("Movie info","Login user mail:${user.email!!}")
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        else
        {
            Log.d("Movie info","Not login")
            intent=Intent(this,LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
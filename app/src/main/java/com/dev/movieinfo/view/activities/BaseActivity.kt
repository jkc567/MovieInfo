package com.dev.movieinfo.view.activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dev.movieinfo.R
import com.dev.movieinfo.utils.LoadingDialogUtil

open class BaseActivity:AppCompatActivity() {
     var diag:LoadingDialogUtil?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        onBackPressedDispatcher.addCallback(this, object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                moveTaskToBack(true)
            }

        })
        super.onCreate(savedInstanceState)
    }
    fun showLoading()
    {
        if(diag==null)
        {
            diag=LoadingDialogUtil.show(this)
        }
        else
        {
            diag?.show()
        }
    }
    fun hideLoading()
    {
        diag?.hide()
    }
    protected open fun showMessage(Mensaje: String?) {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(Mensaje)
            .setPositiveButton(R.string.btnAceptar) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
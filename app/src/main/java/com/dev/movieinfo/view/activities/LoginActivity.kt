package com.dev.movieinfo.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.ActivityLoginBinding
import com.dev.movieinfo.databinding.ActivityMainBinding
import com.dev.movieinfo.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {
    lateinit var _binding:ActivityLoginBinding
    val binding get() = _binding
    val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        _binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel=viewModel
        initUI()
    }
    fun initUI()
    {
        binding.btnContinue.setOnClickListener {
            viewModel.login()
        }
        viewModel.isValidEmail.observe(this){

            binding.inputEmail.isErrorEnabled=!it
            binding.inputEmail.error= if(it) "" else getString(R.string.invalid_email_text)
        }
        viewModel.isValidPassword.observe(this){
            binding.inputPassword.isErrorEnabled=!it
            binding.inputPassword.error=if(it) "" else getString(R.string.invalid_password_text)
        }
        viewModel.loading.observe(this){
            if(it)
            {
                showLoading()
            }
            else
            {
                hideLoading()
            }
        }
        viewModel.onSuccess.observe(this){
            if(it)
            {
                goToMainActivity()
            }
        }
        viewModel.failure.observe(this)
        {
            if(it)
            {
                showMessage(getString(R.string.login_error_message))
            }
        }
    }
    fun goToMainActivity()
    {
        val intent=Intent(this,MainActivity::class.java)
        intent.flags= android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
package com.dev.movieinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.ActivityMainBinding
import com.dev.movieinfo.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var _binding:ActivityMainBinding
    val  binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }
    fun initUI()
    {
        loadMainFragment()
    }
    fun loadMainFragment()
    {
        val fragmentManager=supportFragmentManager
        val fragmenTransaction=fragmentManager.beginTransaction()
        val mainFragment=MainFragment.newInstance()
        fragmenTransaction.replace(R.id.fl_main_layout,mainFragment)
        fragmentManager.popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmenTransaction.commit()
    }
}
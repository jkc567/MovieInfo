package com.dev.movieinfo.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.ActivityMainBinding
import com.dev.movieinfo.view.fragments.MainFragment
import com.dev.movieinfo.viewmodel.MainViewModel

class MainActivity : BaseActivity() {
    lateinit var _binding:ActivityMainBinding
    val  binding get() = _binding!!
    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.itemExit)
        {
            logOut()
        }
        return super.onOptionsItemSelected(item)
    }
    fun initUI()
    {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle(getString(R.string.main_title))
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
    fun logOut()
    {
        val alert=AlertDialog.Builder(this)
            .setTitle(R.string.logout_title)
            .setMessage(R.string.logout_message)
            .setPositiveButton(R.string.btnAceptar){view, _ ->
                if(viewModel.logOut())
                {
                closeSesion()
                }
            }
            .setNegativeButton(R.string.btnCancelar){view, _ ->
                view.dismiss()
            }.create()
        alert.show()
    }
    fun closeSesion()
    {
        val intent=Intent(this,LoginActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
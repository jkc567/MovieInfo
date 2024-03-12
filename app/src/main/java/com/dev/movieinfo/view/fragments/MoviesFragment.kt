package com.dev.movieinfo.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.FragmentMoviesBinding
import com.dev.movieinfo.view.adapters.MoviesAdapter
import com.dev.movieinfo.viewmodel.MoviesViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesFragment : BaseFragment() {
    lateinit var _binding:FragmentMoviesBinding
    val binding get() = _binding
    val moviesAdapter=MoviesAdapter()
    val viewModel:MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View= with(FragmentMoviesBinding.inflate(layoutInflater,container,false))
    {
        lifecycleOwner=viewLifecycleOwner
        _binding=this
        setUI()
        root
    }

    companion object {
        fun newInstance() =
            MoviesFragment().apply {
            }
    }
    fun setUI()
    {

        val user=FirebaseAuth.getInstance().currentUser
        binding.rvMovies.apply {
            adapter=moviesAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }
        moviesAdapter.itemCliclk={
            Log.d("Movies info",it.title)
            replaceFragment(it.id)
        }
        binding.txtUser.text=user!!.email
        viewModel.loading.observe(viewLifecycleOwner){
            if(it)
            {
                showLoading()
            }
            else
            {
                hideLoading()
            }
        }
        viewModel.moviePagin.observe(viewLifecycleOwner){
            moviesAdapter.submitData(lifecycle,it)
        }


    }
    fun replaceFragment(movieId:Int)
    {
        val fragmentManager=requireActivity().supportFragmentManager
        val fragmenTransaction=fragmentManager.beginTransaction()
        val mainFragment=DetailFragment.newInstance(movieId)
        fragmenTransaction.replace(R.id.fl_main_layout,mainFragment)
        fragmenTransaction.addToBackStack(null)
        fragmenTransaction.commit()
    }
}
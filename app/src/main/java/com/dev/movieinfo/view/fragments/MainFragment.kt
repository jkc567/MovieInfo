package com.dev.movieinfo.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var _binding:FragmentMainBinding
    val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View= with(FragmentMainBinding.inflate(layoutInflater,container,false))
    {
        lifecycleOwner=viewLifecycleOwner
        _binding=this
        root
    }

    companion object {
        fun newInstance() =
            MainFragment().apply {
            }
    }
}
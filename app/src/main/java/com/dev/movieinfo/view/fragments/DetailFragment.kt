package com.dev.movieinfo.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.FragmentDetailBinding
import com.dev.movieinfo.viewmodel.DetailViewModel


private const val ARG_PARAM1 = "movieId"
class DetailFragment : BaseFragment() {
    private lateinit var _binding:FragmentDetailBinding
    val binding get() = _binding
    private var movieId: Int? = null
    val viewModel:DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentDetailBinding.inflate(inflater,container,false)){
        lifecycleOwner=viewLifecycleOwner
        viewmodel=viewModel
        _binding=this
        setUI()
        root
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, movieId)
                }
            }
    }
    fun setUI()
    {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            viewModel.getMovieDetail(movieId!!)
            viewModel.movieDetail.observe(viewLifecycleOwner){
                Glide.with(requireContext()).load("${binding.root.resources.getString(R.string.base_image_path)}${it.backdrop_path}").
                into(binding.imgBackDrop)
                binding.txtRuntime.text="${it.runtime} mins"
                binding.txtClassification.text=if(it.adult) getString(R.string.only_adult_text) else getString(R.string.all_family_title)
                val genresList=it.genres.map { it.name }.toTypedArray().joinToString("\n")
                binding.txtGenres.text=genresList

            }
    }
}
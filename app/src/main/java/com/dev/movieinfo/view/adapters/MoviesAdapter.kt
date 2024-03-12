package com.dev.movieinfo.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.movieinfo.R
import com.dev.movieinfo.databinding.MoviesCardItemBinding
import com.dev.movieinfo.model.local.MoviesModel
import java.text.DecimalFormat

class MoviesAdapter:PagingDataAdapter<MoviesModel,MoviesAdapter.MoviesViewHolder>(itemCallback) {
    var itemCliclk:(MoviesModel)->Unit={}
    var decimalFormat=DecimalFormat("##.0")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding=MoviesCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }
    inner class MoviesViewHolder(val binding:MoviesCardItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(movie:MoviesModel)
        {
            binding.txtTitle.text=movie.title
            binding.txtAverage.text=decimalFormat.format(movie.vote_average)
            if(movie.poster_path!=null) {
                Glide.with(binding.root)
                    .load("${binding.root.resources.getString(R.string.base_image_path)}${movie.poster_path}")
                    .into(binding.imgPoster)
            }
            else
            {
                binding.imgPoster.setImageResource(R.drawable.outline_image_24)
            }
            binding.root.setOnClickListener {
                itemCliclk(movie)
            }

        }
    }
    object itemCallback: DiffUtil.ItemCallback<MoviesModel>(){
        override fun areItemsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem.id==newItem.id
        }

    }
}
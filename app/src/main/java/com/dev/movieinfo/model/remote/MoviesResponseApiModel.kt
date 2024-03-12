package com.dev.movieinfo.model.remote

import com.dev.movieinfo.model.local.MoviesModel

data class MoviesResponseApiModel(
    val results: List<MoviesModel>
)

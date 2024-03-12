package com.dev.movieinfo.model.remote

import com.dev.movieinfo.model.local.GenreModel

data class GenresResponseApiModel(
    val genres:List<GenreModel>
)

package com.dev.movieinfo.model.local

data class MoviesModel(
    val id:Int,
    val adult:Boolean,
    val title:String,
    val overview:String,
    val genres:List<GenreModel>,
    val poster_path:String?,
    val vote_average:Double,
    val runtime:Int,
    val release_date:String,
    val backdrop_path:String
)

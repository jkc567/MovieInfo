package com.dev.movieinfo.model.service

import com.dev.movieinfo.BuildConfig

class Config {
    companion object{
        const val TOKEN:String=BuildConfig.API_TOKEN
        const val API_VERSION:String="3"
        const val URL_BASE:String="https://api.themoviedb.org/$API_VERSION/";

        const val NOW_PLAYING:String="movie/now_playing"
        const val GENRE:String="genre/movie/list"
        const val DETAILS:String="movie/{id}"
    }

}
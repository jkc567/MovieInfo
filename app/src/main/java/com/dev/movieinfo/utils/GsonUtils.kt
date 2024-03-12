package com.dev.movieinfo.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonUtils {
    companion object
    {
        val gson=Gson()
        fun toJson(value:Any):String
        {
            return gson.toJson(value)
        }
        fun fromJson(value:String,type:TypeToken<Any>)
        {
            return gson.fromJson(value,type.type)
        }
    }
}
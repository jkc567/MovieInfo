package com.dev.movieinfo.model.service
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Service {
    var apiRetrofit: Retrofit?=null
    companion object{
        var instance: Service?=null

        fun Instance(): Service {
            if(instance !=null)
            {
                return  instance!!
            }
            synchronized(this)
            {
                instance = Service()
            }
            return instance!!
        }
    }
     fun GetApiRetroFit():Retrofit
    {
        if(apiRetrofit!=null){
            return apiRetrofit!!
        }else{
            val logging: HttpLoggingInterceptor=HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            var httpClient: OkHttpClient.Builder=OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.readTimeout(30,TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.addInterceptor{
                val original:Request=it.request()
                val request:Request=original.newBuilder()
                    .header("Content-Type","application/json")
                    .header("Accept","application/json")
                    .header("Authorization","Bearer ${Config.TOKEN}")
                    .method(original.method, original.body)
                    .build()
                it.proceed(request)

            }
            apiRetrofit=Retrofit.Builder()
                .baseUrl(Config.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

            return  apiRetrofit!!

        }
    }
    fun getApiService():ApiService
    {
        GetApiRetroFit();
        return apiRetrofit!!.create(ApiService::class.java)
    }
}
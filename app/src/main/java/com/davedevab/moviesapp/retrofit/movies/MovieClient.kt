package com.davedevab.moviesapp.retrofit.movies

import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.retrofit.models.movies.MovieInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieClient {
    private val movieService: MovieService
    private val retrofit: Retrofit

    companion object{
        var instance: MovieClient? = null
            get() {
                if (field == null){
                    instance = MovieClient()
                }
                return field
            }
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(MovieInterceptor())

        val client = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        movieService = retrofit.create(MovieService::class.java)
    }

    fun getMovieService() = movieService

}
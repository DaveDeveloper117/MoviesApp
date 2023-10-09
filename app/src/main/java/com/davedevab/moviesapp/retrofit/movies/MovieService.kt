package com.davedevab.moviesapp.retrofit.movies

import com.davedevab.moviesapp.retrofit.models.movies.PopularMoviesResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/top_rated")
    fun getPopularMovies(): retrofit2.Call<PopularMoviesResponse>
}
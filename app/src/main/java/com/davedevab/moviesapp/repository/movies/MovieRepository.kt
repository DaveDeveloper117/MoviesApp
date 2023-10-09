package com.davedevab.moviesapp.repository.movies

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.davedevab.moviesapp.common.App
import com.davedevab.moviesapp.retrofit.movies.MovieClient
import com.davedevab.moviesapp.retrofit.movies.MovieService
import com.davedevab.moviesapp.retrofit.models.movies.Movie
import com.davedevab.moviesapp.retrofit.models.movies.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    var movieService: MovieService? = null
    var movieClient: MovieClient?= null
    var popularMovies: MutableLiveData<List<Movie>> ?= null

    init {
        movieClient = MovieClient.instance
        movieService = movieClient?.getMovieService()
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>?{
        if (popularMovies == null){
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse>? = movieService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(App.instance, "Something went wrong, please check your internet connection", Toast.LENGTH_LONG).show()
            }

        })

        return popularMovies
    }
}
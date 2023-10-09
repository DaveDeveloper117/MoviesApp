package com.davedevab.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.davedevab.moviesapp.repository.movies.MovieRepository
import com.davedevab.moviesapp.retrofit.models.movies.Movie

class MovieViewModel: ViewModel() {
    private var movieRepository: MovieRepository
    private var popularMovies: LiveData<List<Movie>>

    init {
        movieRepository = MovieRepository()
        popularMovies = movieRepository?.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovies
    }
}
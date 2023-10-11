package com.davedevab.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.davedevab.moviesapp.repository.movies.MovieRepository
import com.davedevab.moviesapp.retrofit.models.movies.Movie

class MovieViewModel: ViewModel() {

    /**
     * Esta clase actúa como un intermediario entre la interfaz de usuario y la capa de repositorio.
     * Almacena los datos de películas populares en popularMovies, que se obtienen de la capa de repositorio a través de MovieRepository.
     * Expone los datos de películas populares como una propiedad pública a través de la función getPopularMovies().
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    private var movieRepository: MovieRepository
    private var popularMovies: LiveData<List<Movie>>

    init {
        movieRepository = MovieRepository()
        popularMovies = movieRepository?.popularMovies()!!
    }

    /**
     * Obtiene los datos de películas populares de la capa de repositorio.
     *
     * @return La lista de películas populares.
     */
    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovies
    }
}
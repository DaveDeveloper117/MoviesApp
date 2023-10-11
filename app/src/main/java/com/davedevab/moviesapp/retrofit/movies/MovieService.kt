package com.davedevab.moviesapp.retrofit.movies

import com.davedevab.moviesapp.retrofit.models.movies.PopularMoviesResponse
import retrofit2.http.GET

interface MovieService {

    /**
     * Define el método que se utilizará para realizar solicitudes a la API de TheMovieDB.
     * En este caso, el método getPopularMovies() se encarga de obtener películas populares.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    @GET("movie/top_rated")
    fun getPopularMovies(): retrofit2.Call<PopularMoviesResponse>
}
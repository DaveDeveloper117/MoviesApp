package com.davedevab.moviesapp.repository.movies

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.davedevab.moviesapp.common.App
import com.davedevab.moviesapp.retrofit.models.movies.Movie
import com.davedevab.moviesapp.retrofit.models.movies.PopularMoviesResponse
import com.davedevab.moviesapp.retrofit.movies.MovieClient
import com.davedevab.moviesapp.retrofit.movies.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    /**
     * Esta clase se encarga de gestionar las solicitudes de películas populares desde la API de TheMovieDB y proporcionar los resultados a la interfaz de usuario a través de un objeto MutableLiveData.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    var movieService: MovieService? = null
    var movieClient: MovieClient?= null

    /**
     * Uso de MutableLiveData: El uso de MutableLiveData es apropiado para mantener los datos observables y notificar a los componentes de la interfaz de usuario cuando cambian los datos.
     * Esto permite una comunicación efectiva entre la capa de datos y la interfaz de usuario.
     * */
    var popularMovies: MutableLiveData<List<Movie>> ?= null

    // Inicialización de propiedades
    init {
        movieClient = MovieClient.instance
        movieService = movieClient?.getMovieService()
        popularMovies = popularMovies()
    }

    /**
    *Método popularMovies(): Este método se encarga de obtener las películas populares de la API y actualizar el objeto popularMovies con los resultados.
    *Utilizas enqueue para realizar la solicitud en segundo plano y manejar tanto las respuestas exitosas como las fallas.
    */
    fun popularMovies(): MutableLiveData<List<Movie>>?{
        if (popularMovies == null){
            popularMovies = MutableLiveData<List<Movie>>()
        }
        // Obtiene las películas populares de la API
        val call: Call<PopularMoviesResponse>? = movieService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    // Actualiza el objeto popularMovies con los resultados
                    popularMovies?.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                // Muestra un mensaje de Toast en caso de que la solicitud falle
                Toast.makeText(App.instance, "Something went wrong, please check your internet connection", Toast.LENGTH_LONG).show()
            }

        })

        return popularMovies
    }
}
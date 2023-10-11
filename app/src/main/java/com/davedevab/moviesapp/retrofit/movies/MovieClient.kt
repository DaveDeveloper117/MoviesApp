package com.davedevab.moviesapp.retrofit.movies

import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.retrofit.models.movies.MovieInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieClient {

    /**
     * Esta clase proporciona una interfaz para realizar solicitudes a la API de TheMovieDB. Utiliza un patrón Singleton para asegurarte de que solo haya una instancia de MovieClient en tu aplicación.
     * En el constructor init, estás configurando un cliente de OkHttp con el MovieInterceptor para interceptar las solicitudes y agregar parámetros de consulta, como la clave de la API y el idioma. Luego, creas una instancia de Retrofit, especificando la URL base, el convertidor Gson para la serialización/deserialización de datos y el cliente OkHttp que has configurado.
     * La función getMovieService() te proporciona una instancia de MovieService que puedes utilizar para realizar solicitudes a la API. Esto encapsula la creación de la interfaz de servicio Retrofit.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */


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
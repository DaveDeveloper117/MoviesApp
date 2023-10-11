package com.davedevab.moviesapp.retrofit.models.movies

data class PopularMoviesResponse(

    /**
     * Esta clase representa la respuesta que se obtiene al solicitar películas populares.
     * Contiene información sobre la página actual, la lista de películas, el número total de páginas y el número total de resultados.
     * Es importante que esta clase coincida con la estructura de la respuesta real de la API.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
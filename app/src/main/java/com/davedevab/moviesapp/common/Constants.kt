package com.davedevab.moviesapp.common

class Constants {

    /**
     * Esta clase define constantes que se utilizan en toda la aplicación,
     * como la URL base de la API, la clave de la API,
     * URL para imágenes y otros valores relacionados con la API.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "4eec76fcdd65b57dc00290ce981b560b"
        const val IMAGE_ORIGINAL_BASE_URL = "https://image.tmdb.org/t/p/original/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val URL_PARAM_API_KEY = "api_key"
        const val URL_PARAM_LANG = "language"
    }
}
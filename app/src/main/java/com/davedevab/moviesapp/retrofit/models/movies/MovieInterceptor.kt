package com.davedevab.moviesapp.retrofit.models.movies

import com.davedevab.moviesapp.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor: Interceptor {

    /**
     * Esta clase implementa la interfaz Interceptor de OkHttp y se utiliza para interceptar las solicitudes HTTP realizadas por Retrofit.
     * En este caso, estás agregando parámetros de consulta a cada solicitud, como la clave de la API y el idioma.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY)
            .addQueryParameter(Constants.URL_PARAM_LANG, "en-US")
            .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}
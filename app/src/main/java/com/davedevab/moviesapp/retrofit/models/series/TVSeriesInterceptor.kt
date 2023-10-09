package com.davedevab.moviesapp.retrofit.models.series

import com.davedevab.moviesapp.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TVSeriesInterceptor: Interceptor {

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
package com.davedevab.moviesapp.retrofit.series

import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.retrofit.models.series.TVSeriesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class TVSeriesClient {
    private val tvSeriesService: TVSeriesService
    private val retrofit: Retrofit

    companion object{
        var instance: TVSeriesClient? = null
            get() {
                if (field == null){
                    instance = TVSeriesClient()
                }
                return field
            }
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TVSeriesInterceptor())

        val client = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client((client))
            .build()

        tvSeriesService = retrofit.create(TVSeriesService::class.java)
    }

    fun getTVSeriesService() = tvSeriesService

}
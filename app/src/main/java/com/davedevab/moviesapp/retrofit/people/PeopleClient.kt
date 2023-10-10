package com.davedevab.moviesapp.retrofit.people

import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.retrofit.models.people.PeopleInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeopleClient {
    private  val peopleService: PeopleService
    private val retrofit: Retrofit

    companion object{
        var instance: PeopleClient? = null
            get() {
                if (field == null){
                    instance = PeopleClient()
                }
                return field
            }
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(PeopleInterceptor())

        val client = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        peopleService = retrofit.create(PeopleService::class.java)

    }

    fun getPeopleService() = peopleService

}
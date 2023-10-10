package com.davedevab.moviesapp.retrofit.people

import com.davedevab.moviesapp.retrofit.models.people.PopularPeopleResponse
import retrofit2.http.GET

interface PeopleService {
    @GET("person/popular")
    fun getPopularPeople(): retrofit2.Call<PopularPeopleResponse>
}
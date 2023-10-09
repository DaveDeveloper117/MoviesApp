package com.davedevab.moviesapp.retrofit.series

import com.davedevab.moviesapp.retrofit.models.series.TopTVSeriesResponse
import retrofit2.http.GET

interface TVSeriesService {
    @GET("tv/top_rated")
    fun getTopTVSeries(): retrofit2.Call<TopTVSeriesResponse>
}
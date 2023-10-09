package com.davedevab.moviesapp.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.davedevab.moviesapp.repository.series.TVSeriesRepository
import com.davedevab.moviesapp.retrofit.models.series.TVSeries

class TvSeriesViewModel: ViewModel() {
    private var tvSeriesRepository: TVSeriesRepository
    private var topTVSeries: LiveData<List<TVSeries>>

    init {
        tvSeriesRepository = TVSeriesRepository()
        topTVSeries = tvSeriesRepository?.topTVSeries()!!
    }

    fun getTopTVSeries(): LiveData<List<TVSeries>>{
        return topTVSeries
    }

}
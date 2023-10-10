package com.davedevab.moviesapp.retrofit.models.people

data class PopularPeopleResponse(
    val page: Int,
    val results: List<People>,
    val total_pages: Int,
    val total_results: Int
)
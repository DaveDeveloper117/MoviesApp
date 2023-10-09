package com.davedevab.moviesapp.retrofit.models.movies

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String, // 3 Movie Description
    val popularity: Double,
    val poster_path: String, // 1 Movie Poster
    val release_date: String, // 4 Movie Date
    val title: String, // 2 Movie Title
    val video: Boolean,
    val vote_average: Double, // 5 Movie Rate
    val vote_count: Int
)
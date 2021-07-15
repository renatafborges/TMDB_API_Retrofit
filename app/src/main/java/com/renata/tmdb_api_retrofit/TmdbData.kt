package com.renata.tmdb_api_retrofit
//como nossas infos do network vão ser e guardar temporariamente
data class PopularMovies(
    val results: List<Result>
)

data class Result(
    val id: Int,    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

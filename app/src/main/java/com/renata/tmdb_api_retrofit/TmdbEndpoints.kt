package com.renata.tmdb_api_retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//criar para definir os endpoints da TMDB
//contém os métodos que vão chamar as apis
//pega data como uma resposta do servidor
interface TmdbEndpoints {

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<PopularMovies>

}
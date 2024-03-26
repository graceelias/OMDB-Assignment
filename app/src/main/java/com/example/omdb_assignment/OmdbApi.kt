package com.example.omdb_assignment

import com.example.omdb_assignment.api.Movie
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "17af2d83"

interface OmdbApi {
    @GET("/")

    suspend fun fetchMovie(
        @Query("t") title : String,
        @Query("apikey") apiKey: String = API_KEY
    ): Movie
}
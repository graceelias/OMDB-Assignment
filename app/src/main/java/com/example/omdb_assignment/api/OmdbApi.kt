package com.example.omdb_assignment.api

import retrofit2.http.GET

private const val API_KEY = "yourApiKeyHere"

interface OmdbApi {
    @GET(
        "http://www.omdbapi.com/" +
                "?api_key=$API_KEY" +
                "&t="
    )
    suspend fun fetchMovie(title: String): MovieResponse
}
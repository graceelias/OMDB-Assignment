package com.example.omdb_assignment.api
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "title") val title: String,
    @Json(name = "year") val year: String,
    @Json(name = "poster") val poster:  String
)


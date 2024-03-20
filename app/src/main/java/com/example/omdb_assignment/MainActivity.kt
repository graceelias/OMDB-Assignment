package com.example.omdb_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.omdb_assignment.api.Movie
import com.example.omdb_assignment.api.MovieResponse
import com.example.omdb_assignment.api.OmdbApi
import com.example.omdb_assignment.databinding.ActivityMainBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    //private val omdbAssignmentViewModel: OmdbAssignment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
        val movie = adapter.fromJson(moviesJson)

         */


        binding.button.setOnClickListener{
            // make api call
            lifecycleScope.launch {
                val movies = MoshiFactory.fetchMovie(binding.editText.text.toString())

                binding.title.setText(movies.movies[0].title)
                binding.year.setText(movies.movies[0].year)
                binding.poster.setImageResource(0)
            }
        }
    }

}

object MoshiFactory
{
    private val omdbApi: OmdbApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        omdbApi = retrofit.create()
    }

    suspend fun fetchMovie(title: String): MovieResponse =
        omdbApi.fetchMovie(title)
}

package com.example.omdb_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.omdb_assignment.api.Movie
import com.example.omdb_assignment.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val TAG = "PhotoGalleryFragment"
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    //private val omdbAssignmentViewModel: OmdbAssignment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button.setOnClickListener {
            val title = binding.userInputTitle.text.toString()
            this.lifecycleScope.launch {
                try {
                    val response = MovieRepository.fetchMovie(title)
                    Log.d(TAG, "Response received: $response")
                    if (response.title.isNotBlank()) {
                        loadMovieInfo(response)
                    } else {
                        movieNotFoundToast()
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "Failed to fetch Movie", ex)
                    movieNotFoundToast()
                }
            }
        }
    }

    private fun loadMovieInfo(movieData : Movie) {
        binding.year.text = movieData.year
        binding.title.text = movieData.title
        Picasso.get()
            .load(movieData.poster)
            .into(binding.poster)
    }

    private fun movieNotFoundToast() {
        val toast = Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT)
        toast.show()
    }

}



object MovieRepository {
    private val omdbApi: OmdbApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        omdbApi = retrofit.create<OmdbApi>()

    }

    suspend fun fetchMovie(title: String): Movie =
        omdbApi.fetchMovie(title = title)
}

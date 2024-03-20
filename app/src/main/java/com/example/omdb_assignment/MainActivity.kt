package com.example.omdb_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.omdb_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    //private val omdbAssignmentViewModel: OmdbAssignment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            // make api call
            binding.title.setText("test")
            binding.year.setText("test")
            binding.poster.setImageResource(0)

        }
    }
}
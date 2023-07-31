package com.example.booksimple.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.booksimple.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val viewModel by viewModels<MoviesViewModel>()
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleState()
        viewModel.getMovies()
    }

    private fun handleState() = lifecycleScope.launch{
        viewModel.state.collect{ state ->
            when(state){
                is ViewState.Loading -> {

                }

                is ViewState.Success -> {
                    val movies = state.data
                }

                is ViewState.Error -> {

                }
            }
        }
    }
}
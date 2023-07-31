package com.example.booksimple.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksimple.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val viewModel by viewModels<MoviesViewModel>()
    private lateinit var binding: ActivityMoviesBinding

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleState()
        binding.initViews()
        viewModel.getMovies()
    }

    private fun handleState() = lifecycleScope.launch{
        viewModel.state.collect{ state ->
            when(state){
                is ViewState.Loading -> {

                }

                is ViewState.Success -> {
                    val movies = state.data
                    Log.d("TEST", "MOVIES: $movies")
                    moviesAdapter.submitList(movies)
                }

                is ViewState.Error -> {

                }
            }
        }
    }

    private fun ActivityMoviesBinding.initViews(){
        movieRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MoviesActivity, 2)
            adapter = moviesAdapter
        }
        moviesAdapter.onItemClickListener = { item ->

        }

        binding.toolbar.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
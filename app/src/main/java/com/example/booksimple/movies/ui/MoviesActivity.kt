package com.example.booksimple.movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksimple.databinding.ActivityMoviesBinding
import com.example.booksimple.seats.ui.SeatBookingActivity
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
                    binding.toolbar.progressBar.visibility = View.VISIBLE
                }

                is ViewState.Success -> {
                    val movies = state.data
                    Log.d("TEST", "MOVIES: $movies")
                    moviesAdapter.submitList(movies)
                    binding.toolbar.progressBar.visibility = View.GONE
                }

                is ViewState.Error -> {
                    binding.toolbar.progressBar.visibility = View.GONE

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
            val intent = Intent(this@MoviesActivity, SeatBookingActivity::class.java)
            intent.putExtra(SeatBookingActivity.MOVIE_ID, item.id)
            startActivity(intent)
        }

        binding.toolbar.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
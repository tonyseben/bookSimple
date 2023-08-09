package com.example.booksimple.movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
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
        handleSideEffects()
        binding.initViews()
    }

    private fun handleState() = lifecycleScope.launch{
        viewModel.state.collect{ state ->
            binding.toolbar.progressBar.isVisible = state.isLoading

            Log.d("TEST", "MOVIES: ${state.movies}")
            moviesAdapter.submitList(state.movies)
        }
    }

    private fun handleSideEffects() = lifecycleScope.launch{
        viewModel.effect.collect{ effect ->
            when(effect){
                is MoviesContract.SideEffect.Navigation.ToMovieDetails ->{
                    val intent = Intent(this@MoviesActivity, SeatBookingActivity::class.java)
                    intent.putExtra(SeatBookingActivity.MOVIE_ID, effect.movieId)
                    startActivity(intent)
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
            viewModel.setEvent(MoviesContract.Event.OnMovieClicked(item.id))
        }
        toolbar.backButton.setOnClickListener {
            onBackPressed()
        }
    }


}
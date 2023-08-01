package com.example.booksimple.seats.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksimple.databinding.ActivitySeatBookingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SeatBookingActivity : AppCompatActivity() {

    private val viewModel by viewModels<SeatBookingViewModel>()
    private lateinit var binding: ActivitySeatBookingBinding

    @Inject
    lateinit var seatsAdapter: SeatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleState()
        binding.initViews()

        val movieId = requireNotNull(intent.extras?.getInt(MOVIE_ID))
        viewModel.getSeatsForMovie(movieId)
    }

    private fun ActivitySeatBookingBinding.initViews(){

        val movieTitle = requireNotNull(intent.extras?.getString(MOVIE_TITLE))
        toolbar.titleTextView.text = movieTitle

        seatsRecyclerView.apply {
            layoutManager = GridLayoutManager(this@SeatBookingActivity, 9)
            adapter = seatsAdapter
        }
        seatsAdapter.onItemClickListener = { position ->
            viewModel.seatStatusChange(position)
        }

        toolbar.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleState() = lifecycleScope.launch{
        viewModel.state.collect{ state ->
            when(state){
                is ViewState.Loading -> {
                    binding.toolbar.progressBar.visibility = View.VISIBLE
                }

                is ViewState.Success -> {
                    val seats = state.data
                    Log.d("TEST", "SEATS: $seats")
                    seatsAdapter.submitList(seats)
                    binding.toolbar.progressBar.visibility = View.GONE
                }

                is ViewState.Error -> {
                    binding.toolbar.progressBar.visibility = View.GONE

                }
            }
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
        const val MOVIE_TITLE = "MOVIE_TITLE"
    }
}
package com.example.booksimple.seats.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksimple.R
import com.example.booksimple.databinding.ItemSeatBinding
import com.example.booksimple.seats.domain.model.Seat
import com.example.booksimple.seats.domain.model.Status
import javax.inject.Inject

class SeatsAdapter @Inject constructor() : ListAdapter<Seat, SeatsAdapter.SeatViewHolder>(
    SeatDiffCallback()
) {
    var onItemClickListener: ((item: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        return SeatViewHolder(ItemSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class SeatViewHolder(val binding: ItemSeatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pos: Int) {
            val seat = getItem(pos)
            binding.apply {
                val tint = when(seat.status){
                    Status.Booked -> R.color.booked
                    Status.Selected -> R.color.selected
                    Status.Vacant -> R.color.vacant
                    Status.Empty -> R.color.black
                }
                ImageViewCompat.setImageTintList(seatImageButton,
                    ColorStateList.valueOf(seatImageButton.context.getColor(tint)))

                if(seat.status == Status.Empty){
                    seatImageButton.setImageIcon(null)
                    seatImageButton.isClickable = false
                }

                root.setOnClickListener {
                    onItemClickListener?.invoke(pos)
                }
            }
        }
    }

    class SeatDiffCallback : DiffUtil.ItemCallback<Seat>() {

        override fun areItemsTheSame(oldItem: Seat, newItem: Seat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Seat, newItem: Seat): Boolean {
            return oldItem.status == newItem.status
        }

    }
}
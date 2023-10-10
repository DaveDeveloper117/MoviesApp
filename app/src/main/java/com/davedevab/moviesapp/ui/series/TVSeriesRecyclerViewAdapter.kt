package com.davedevab.moviesapp.ui.series

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.davedevab.moviesapp.R
import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.databinding.FragmentTvSeriesItemBinding
import com.davedevab.moviesapp.retrofit.models.series.TVSeries

class TVSeriesRecyclerViewAdapter() : RecyclerView.Adapter<TVSeriesRecyclerViewAdapter.ViewHolder>() {

    private var tvSeries: List<TVSeries> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTvSeriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tvSeries[position]
        holder.tvTitle.text = item.name
        holder.tvSynopsis.text = item.overview
        holder.tvRelease.text = item.first_air_date
        holder.tvRating.text = item.vote_average.toString()
        holder.ivPoster.load(Constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        holder.ivBackdrop.load(Constants.IMAGE_BASE_URL + item.backdrop_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }

    }

    override fun getItemCount(): Int = tvSeries.size
    fun setData(topTVSeries: List<TVSeries>?){
        tvSeries = topTVSeries!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentTvSeriesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivBackdrop: ImageView = binding.backPoster
        val ivPoster: ImageView = binding.poster
        val tvTitle: TextView = binding.title
        val tvSynopsis: TextView = binding.description
        val tvRelease: TextView = binding.releaseDate
        val tvRating : TextView = binding.rating

    }

}
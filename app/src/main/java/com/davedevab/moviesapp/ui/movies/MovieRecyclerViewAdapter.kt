package com.davedevab.moviesapp.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.davedevab.moviesapp.R
import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.databinding.FragmentMovieItemBinding
import com.davedevab.moviesapp.retrofit.models.movies.Movie

class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvVotes.text = item.vote_count.toString()
        holder.tvSynopsis.text = item.overview
        holder.tvRelease.text = item.release_date
        holder.tvRating.text = item.vote_average.toString()
        holder.ivPoster.load(Constants.IMAGE_ORIGINAL_BASE_URL + item.backdrop_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        holder.ivPosterMini.load(Constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        holder.ivBackdrop.load(Constants.IMAGE_ORIGINAL_BASE_URL + item.backdrop_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }

    }

    override fun getItemCount(): Int = movies.size
    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivBackdrop: ImageView = binding.backPoster
        val ivPoster: ImageView = binding.poster
        val ivPosterMini: ImageView = binding.posterMini
        val tvTitle: TextView = binding.title
        val tvVotes: TextView = binding.voteCount
        val tvSynopsis: TextView = binding.description
        val tvRelease: TextView = binding.releaseDate
        val tvRating : TextView = binding.rating

    }

}
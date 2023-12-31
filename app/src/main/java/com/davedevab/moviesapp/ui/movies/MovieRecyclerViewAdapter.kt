package com.davedevab.moviesapp.ui.movies

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.davedevab.moviesapp.R
import com.davedevab.moviesapp.common.Constants
import com.davedevab.moviesapp.databinding.FragmentMovieItemBinding
import com.davedevab.moviesapp.retrofit.models.movies.Movie

class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    /**
     * Clase MovieRecyclerViewAdapter
     * Este adaptador se encarga de inflar elementos de la lista de películas y establecer los datos en función de los objetos Movie.
     * La carga de imágenes desde la URL de la película se maneja de manera eficiente utilizando la biblioteca Coil.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

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
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        // Establece el los elementos de texto de la pelicula
        holder.tvTitle.text = item.title
        holder.tvVotes.text = item.vote_count.toString()
        holder.tvSynopsis.text = item.overview
        holder.tvRelease.text = item.release_date
        holder.tvRating.text = item.vote_average.toString()
        // Carga las imagenes de la película
        holder.ivPoster.load(Constants.IMAGE_ORIGINAL_BASE_URL + item.backdrop_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        holder.ivPosterMini.load(Constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        holder.ivBackdrop.load(Constants.IMAGE_BASE_URL + item.backdrop_path){
            crossfade(true)
            placeholder(R.drawable.placeholder_load)
        }
        val blur = RenderEffect.createBlurEffect(50F, 50F, Shader.TileMode.CLAMP)
        holder.ivBackdrop.setRenderEffect(blur)
    }
    override fun getItemCount(): Int = movies.size

    /**
     * Actualiza los datos del adaptador.
     *
     * @param popularMovies La nueva lista de películas.
     */
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
package com.davedevab.moviesapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.davedevab.moviesapp.R
import com.davedevab.moviesapp.retrofit.models.movies.Movie

class MovieListFragment : Fragment() {

    /**
     * Esta clase muestra una lista de películas populares en un RecyclerView.
     *
     * @author DaveDev117
     * @version 1.0
     * @since 10/10/2023
     */

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()
    private var columnCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicialización de componentes
        val view = inflater.inflate(R.layout.fragment_movie_item_list, container, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(view as RecyclerView?)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        // Creación del adaptador
        movieAdapter = MovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }

        // Obtención de datos de la capa de datos
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            // Actualización del adaptador
            movieAdapter.setData(popularMovies)
        })

        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
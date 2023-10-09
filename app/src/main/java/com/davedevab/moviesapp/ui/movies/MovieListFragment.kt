package com.davedevab.moviesapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import com.davedevab.moviesapp.R
import com.davedevab.moviesapp.retrofit.models.movies.Movie

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(view as RecyclerView?)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
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

        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
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
package com.davedevab.moviesapp.ui.people

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
import com.davedevab.moviesapp.retrofit.models.people.People

/**
 * A fragment representing a list of Items.
 */
class PeopleListFragment : Fragment() {

    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var peopleAdapter: PeopleListRecyclerViewAdapter
    private var popularPeople: List<People> = ArrayList()
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_people_item_list, container, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(view as RecyclerView?)
        peopleViewModel = ViewModelProvider(this).get(PeopleViewModel::class.java)
        peopleAdapter = PeopleListRecyclerViewAdapter()
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = peopleAdapter
            }
        }

        peopleViewModel.getPopularPeople().observe(viewLifecycleOwner, Observer{
            popularPeople = it
            peopleAdapter.setData(popularPeople)
        })

        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
                PeopleListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
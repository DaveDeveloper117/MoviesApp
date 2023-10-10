package com.davedevab.moviesapp.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.davedevab.moviesapp.repository.people.PeopleRepository
import com.davedevab.moviesapp.retrofit.models.people.People

class PeopleViewModel: ViewModel() {
    private var peopleRepository: PeopleRepository
    private var popularPeople: LiveData<List<People>>

    init {
        peopleRepository = PeopleRepository()
        popularPeople = peopleRepository?.popularPeople()!!
    }

    fun getPopularPeople(): LiveData<List<People>>{
        return popularPeople
    }
}
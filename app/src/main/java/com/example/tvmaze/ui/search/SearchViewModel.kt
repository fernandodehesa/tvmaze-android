package com.example.tvmaze.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.data.show.repository.ShowRepository
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val showRepository = ShowRepository()
    val shows = MutableLiveData<List<ShowEntity>>()
    val loading = MutableLiveData(false)

    fun getSearch(query: String){
        viewModelScope.launch {
            loading.postValue(true)
            shows.postValue(showRepository.getSearch(query))
            loading.postValue(false)
        }
    }
}
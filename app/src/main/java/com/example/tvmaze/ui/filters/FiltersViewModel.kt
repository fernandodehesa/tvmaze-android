package com.example.tvmaze.ui.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.data.country.repository.CountryRepository
import kotlinx.coroutines.launch
import java.util.*

class FiltersViewModel: ViewModel() {

    private val countryRepository = CountryRepository()

    val loading = MutableLiveData(false)
    val countries = MutableLiveData<List<CountryEntity>>()
    val date = MutableLiveData(Calendar.getInstance().time)
    val country = MutableLiveData<CountryEntity>()

    init {
        getCountries()
    }

    fun getCountries(){
        viewModelScope.launch {
            loading.postValue(true)

            val countriesList = countryRepository.getCountries()
            countries.postValue(countriesList)

            loading.postValue(false)
        }
    }
}
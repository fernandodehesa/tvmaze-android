package com.example.tvmaze.ui.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.data.show.entity.ScheduleEntity
import com.example.tvmaze.data.show.repository.ShowRepository
import com.example.tvmaze.utils.CommonUtils
import kotlinx.coroutines.launch
import java.util.*

class ScheduleViewModel : ViewModel() {

    private val showRepository = ShowRepository()

    val loading = MutableLiveData(false)
    val schedules = MutableLiveData<List<ScheduleEntity>>()
    val country = MutableLiveData<CountryEntity>(CountryEntity("United States of America", "US"))
    val date = MutableLiveData(Calendar.getInstance().time)

    init {
        getSchedule(country.value!!, date.value!!)
    }

    fun getSchedule(country: CountryEntity, date: Date){
        viewModelScope.launch {
            loading.postValue(true)
            schedules.postValue(showRepository.getSchedule(country.code, CommonUtils.dateToString(date, "yyyy-MM-dd")))
            loading.postValue(false)
        }
    }
}
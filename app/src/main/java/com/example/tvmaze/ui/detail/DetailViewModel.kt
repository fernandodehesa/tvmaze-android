package com.example.tvmaze.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.data.show.entity.CastEntity
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.data.show.repository.ShowRepository
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val showRepository = ShowRepository()
    val loading = MutableLiveData(false)
    val show = MutableLiveData<ShowEntity>()
    val cast = MutableLiveData<List<CastEntity>>()

    var loadingDetail = false
    var loadingCast = false

    fun getShow(id: Int){
        viewModelScope.launch {
            setLoading(loadingDetail = true)
            show.postValue(showRepository.getShow(id))
            setLoading(loadingDetail = false)
        }
    }

    fun getCast(showId: Int){
        viewModelScope.launch {
            setLoading(loadingCast = true)
            cast.postValue(showRepository.getCast(showId))
            setLoading(loadingCast = false)
        }
    }

    private fun setLoading(loadingDetail: Boolean = this.loadingDetail, loadingCast: Boolean = this.loadingCast){

        this.loadingDetail = loadingDetail
        this.loadingCast = loadingCast

        if(this.loadingDetail || this.loadingCast) loading.postValue(true)
        else loading.postValue(false)
    }
}
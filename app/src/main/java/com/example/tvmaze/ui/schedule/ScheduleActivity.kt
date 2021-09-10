package com.example.tvmaze.ui.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.ui.search.SearchActivity
import com.example.tvmaze.data.show.entity.ScheduleEntity
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.databinding.ActivityScheduleBinding
import com.example.tvmaze.ui.common.BaseActivity
import com.example.tvmaze.ui.detail.DetailActivity
import com.example.tvmaze.ui.filters.FiltersActivity
import com.example.tvmaze.utils.AppUtils
import java.util.*

class ScheduleActivity : BaseActivity() {

    companion object {
        const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
        const val EXTRA_DATE = "EXTRA_DATE"
    }

    private lateinit var binding: ActivityScheduleBinding
    private val scheduleViewModel: ScheduleViewModel by viewModels()

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            val country = data?.getSerializableExtra(EXTRA_COUNTRY) as CountryEntity?
            val date = data?.getSerializableExtra(EXTRA_DATE) as Date?

            if(country != null && date != null){
                scheduleViewModel.country.postValue(country)
                scheduleViewModel.date.postValue(date)
                scheduleViewModel.getSchedule(country, date)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = AppUtils.showSpinnerDialog(this)

        setupHeader()
        setupRecyclerView()
        observeSchedule()
        observeLoading()
        observeDate()
    }

    private fun setupHeader(){
        binding.header.binding.btnSearch.setOnClickListener { goToSearch() }
        binding.header.binding.btnFilter.setOnClickListener { goToFilters() }
    }

    private fun setupRecyclerView(){
        val adapter = ScheduleAdapter(mutableListOf())
        adapter.setItemTapListener(object: ScheduleAdapter.OnItemTap{
            override fun onTap(schedule: ScheduleEntity) {
                goToDetail(schedule.show)
            }
        })

        binding.rvSchedule.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@ScheduleActivity)
        }
    }

    private fun observeSchedule(){
        scheduleViewModel.schedules.observe(this, { schedules ->
            binding.rvSchedule.adapter.let {
                if(it is ScheduleAdapter) it.updateList(schedules)
            }
            binding.tvEmptyList.visibility = if(schedules.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    private fun observeLoading(){
        scheduleViewModel.loading.observe(this, {
            AppUtils.showSpinner(spinner, it)
        })
    }

    private fun observeDate(){
        scheduleViewModel.date.observe(this, {
            binding.header.setDate(it)
        })
    }

    private fun goToFilters(){
        val intent = Intent(this, FiltersActivity::class.java)
        intent.putExtra(EXTRA_COUNTRY, scheduleViewModel.country.value)
        intent.putExtra(EXTRA_DATE, scheduleViewModel.date.value)
        resultLauncher.launch(intent)
    }

    private fun goToSearch(){
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private fun goToDetail(show: ShowEntity){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("show", show)
        startActivity(intent)
    }
}
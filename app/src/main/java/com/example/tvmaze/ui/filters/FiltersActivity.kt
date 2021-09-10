package com.example.tvmaze.ui.filters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.databinding.ActivityFiltersBinding
import com.example.tvmaze.ui.common.BaseActivity
import com.example.tvmaze.ui.common.DatePickerFragment
import com.example.tvmaze.ui.schedule.ScheduleActivity
import com.example.tvmaze.utils.AppUtils
import com.example.tvmaze.utils.CommonUtils
import java.util.*

class FiltersActivity : BaseActivity() {

    private lateinit var binding: ActivityFiltersBinding
    private val filtersViewModel: FiltersViewModel by viewModels()

    private val DEFAULT_DATE = Calendar.getInstance().time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFilters()
        setupInputDate()
        setupInputCountry()
        setupRecyclerView()
        setupBtnApply()

        observeFilters()
        observeCountries()
        observeLoading()
    }

    private fun setupFilters(){
        val country = intent.extras?.get(ScheduleActivity.EXTRA_COUNTRY) as CountryEntity?
        val date = intent.extras?.get(ScheduleActivity.EXTRA_DATE) as Date?

        if(country != null) filtersViewModel.country.postValue(country)
        if(date != null) filtersViewModel.date.postValue(date)
    }

    private fun setupInputDate(){
        binding.tvDate.text = CommonUtils.dateToString(DEFAULT_DATE)
        binding.inputDate.setOnClickListener { showDatePicker() }
    }

    private fun setupInputCountry(){

        binding.etCountry.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (binding.rvCountries.adapter as CountryAdapter).filter.filter(newText)
                return false
            }
        })
    }

    private fun setupRecyclerView(){
        val adapter = CountryAdapter(mutableListOf())
        adapter.setItemTapListener(object: CountryAdapter.OnItemTap{
            override fun onTap(country: CountryEntity) {
                filtersViewModel.country.postValue(country)
            }
        })

        binding.rvCountries.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@FiltersActivity)
        }
    }

    private fun setupBtnApply(){
        binding.btnApply.setOnClickListener {

            val country = filtersViewModel.country.value
            val date = filtersViewModel.date.value

            val resultIntent = Intent()
            resultIntent.putExtra(ScheduleActivity.EXTRA_COUNTRY, country)
            resultIntent.putExtra(ScheduleActivity.EXTRA_DATE, date)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun observeFilters(){
        filtersViewModel.country.observe(this, {
            binding.tvCountrySelected.text = it.name
        })
        filtersViewModel.date.observe(this, {
            binding.tvDate.text = CommonUtils.dateToString(it)
        })
    }

    private fun observeCountries(){
        filtersViewModel.countries.observe(this, { countries ->
            binding.rvCountries.adapter.let {
                if(it is CountryAdapter) it.updateList(countries)
            }
        })
    }

    private fun observeLoading(){
        filtersViewModel.loading.observe(this, {
            AppUtils.showSpinner(spinner, it)
        })
    }

    private fun showDatePicker(){
        val newFragment = DatePickerFragment.newInstance{ _, year, month, day ->
            filtersViewModel.date.postValue(CommonUtils.datePartsToDate(year, month, day))
        }

        newFragment.show(supportFragmentManager, "datePicker")
    }
}
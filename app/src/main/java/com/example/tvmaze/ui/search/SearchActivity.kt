package com.example.tvmaze.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.databinding.ActivitySearchBinding
import com.example.tvmaze.ui.common.BaseActivity
import com.example.tvmaze.ui.common.header.HeaderSearch
import com.example.tvmaze.ui.detail.DetailActivity
import com.example.tvmaze.utils.AppUtils

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val DEFAULT_QUERY = "Game Shakers"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = AppUtils.showSpinnerDialog(this)

        setupRecyclerView()
        setupSearch()
        observeShows()
        observeLoading()
    }

    private fun setupRecyclerView(){
        val adapter = SearchAdapter(mutableListOf())
        adapter.setItemTapListener(object: SearchAdapter.OnItemTap{
            override fun onTap(show: ShowEntity) {
                goToDetail(show)
            }
        })

        binding.rvShows.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun setupSearch() {

        binding.header.binding.etTitle.setText(DEFAULT_QUERY)
        searchViewModel.getSearch(binding.header.binding.etTitle.text.toString())

        binding.header.setSearchListener(object : HeaderSearch.OnSearch{
            override fun onSearch(query: String) {
                searchViewModel.getSearch(query)
            }
        })
    }

    private fun observeShows(){
        searchViewModel.shows.observe(this, { shows ->
            binding.rvShows.adapter.let {
                if(it is SearchAdapter) it.updateList(shows)
            }
            binding.tvEmptyList.visibility = if(shows.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    private fun observeLoading(){
        searchViewModel.loading.observe(this, {
            AppUtils.showSpinner(spinner, it)
        })
    }

    private fun goToDetail(show: ShowEntity){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("show", show)
        startActivity(intent)
    }
}
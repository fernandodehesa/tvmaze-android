package com.example.tvmaze.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.tvmaze.R
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.databinding.ActivityDetailBinding
import com.example.tvmaze.ui.common.BaseActivity
import com.example.tvmaze.utils.AppUtils

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupShow()
        setupRecyclerView()
        observe()
        observeLoading()
    }

    private fun setupShow(){
        val show = intent.extras?.get("show") as ShowEntity
        detailViewModel.show.postValue(show)

        detailViewModel.getShow(show.id)
        detailViewModel.getCast(show.id)
    }

    private fun setupRecyclerView(){
        val adapter = CastAdapter(mutableListOf())
        binding.rvCast.apply {
            this.adapter = adapter
            if(resources.getBoolean(R.bool.portrait_only)){
                this.layoutManager = GridLayoutManager(this@DetailActivity, 3, GridLayoutManager.VERTICAL, false)
            }else{
                this.layoutManager = GridLayoutManager(this@DetailActivity, 5, GridLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun observe(){
        observeShow()
        observeCast()
    }

    private fun observeShow(){
        detailViewModel.show.observe(this, {show ->
            binding.tvName.text = show.name
            binding.tvNetworkName.text = show.name
            binding.tvRatingAverage.text = getString(R.string.label_rating, show.ratingFormatted)
            binding.tvSummary.text = AppUtils.HtmlToText(show.summary)
            binding.tvGenres.text = show.genresLine
            binding.tvScheduleTimeDays.text = show.scheduleTimeDays
            Glide.with(this).load(show.image).centerCrop().into(binding.ivImage)

            binding.btnSite.setOnClickListener {
                if(!show.officialSite.isNullOrEmpty()) AppUtils.openBrowser(show.officialSite!!)
            }

            binding.header.title = show.name
        })
    }

    private fun observeCast(){
        detailViewModel.cast.observe(this, { cast ->
            binding.tvLabelCast.visibility = if (cast.isNotEmpty()) View.VISIBLE else View.GONE
            binding.rvCast.adapter.let {
                if (it is CastAdapter) it.updateList(cast)
            }
        })
    }

    private fun observeLoading(){
        detailViewModel.loading.observe(this, {
            AppUtils.showSpinner(spinner, it)
        })
    }

}
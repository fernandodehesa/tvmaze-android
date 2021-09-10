package com.example.tvmaze.ui.common.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.tvmaze.R
import com.example.tvmaze.databinding.ViewHeaderDetailBinding

class HeaderDetail(context: Context, private val attrs: AttributeSet?): Header(context, attrs) {

    private val binding = ViewHeaderDetailBinding.inflate(LayoutInflater.from(context), this)

    var title: String? = ""
        set(value) {
            field = value
            binding.tvTitle.text = value ?: ""
        }

    init {
        setupLayout()
        setupAttrs()

        binding.btnBack.setOnClickListener { onBackClick() }
    }

    private fun setupAttrs(){
        val styledAttrs = context.obtainStyledAttributes(this.attrs, R.styleable.HeaderDetail)
        title = styledAttrs.getString(R.styleable.HeaderDetail_title) ?: title
        styledAttrs.recycle()
    }
}
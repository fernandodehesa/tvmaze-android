package com.example.tvmaze.ui.common.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.tvmaze.databinding.ViewHeaderSearchBinding

class HeaderSearch(context: Context, attrs: AttributeSet?): Header(context, attrs) {

    private var onSearchListener: OnSearch? = null

    fun setSearchListener(l: OnSearch){
        onSearchListener = l
    }

    val binding = ViewHeaderSearchBinding.inflate(LayoutInflater.from(context), this)

    init {
        setupLayout()
        setupOnSearch()

        binding.btnBack.setOnClickListener { onBackClick() }
    }

    private fun setupOnSearch(){

        binding.btnSearch.setOnClickListener {
            performSearch()
        }

        binding.etTitle.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                performSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun performSearch(){
        onSearchListener?.onSearch(binding.etTitle.text.toString())
        binding.etTitle.clearFocus()

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etTitle.windowToken, 0)
    }

    interface OnSearch {
        fun onSearch(query: String)
    }
}
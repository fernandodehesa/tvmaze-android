package com.example.tvmaze.ui.common.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.tvmaze.databinding.ViewHeaderScheduleBinding
import java.text.SimpleDateFormat
import java.util.*

class HeaderSchedule(context: Context, attrs: AttributeSet?): Header(context, attrs) {

    val binding = ViewHeaderScheduleBinding.inflate(LayoutInflater.from(context), this)

    private var date = Calendar.getInstance().time

    fun setDate(value: Date){
        date = value
        binding.tvTitle.text = parseDate(date)
    }

    init {
        setupLayout()
        setDate(date)
    }

    private fun parseDate(date: Date): String {
        val formatter = SimpleDateFormat("EEEE d 'de' MMMM yyyy", Locale("es", "MX"))
        return formatter.format(date)
    }
}
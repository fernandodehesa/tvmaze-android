package com.example.tvmaze.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvmaze.data.show.entity.ScheduleEntity
import com.example.tvmaze.databinding.ItemScheduleBinding

class ScheduleAdapter(private val schedules: MutableList<ScheduleEntity>): RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>() {

    private var onTapListener: OnItemTap? = null

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    fun updateList(_schedules: List<ScheduleEntity>){
        schedules.clear()
        schedules.addAll(_schedules)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) = holder.bind(schedules[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val view = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleHolder(view)
    }

    inner class ScheduleHolder(private val itemBinding: ItemScheduleBinding): RecyclerView.ViewHolder(itemBinding.root){

        private val context = itemBinding.root.context

        fun bind(schedule: ScheduleEntity){
            itemBinding.tvName.text = schedule.show.name
            itemBinding.tvNetworkName.text = schedule.show.networkName
            itemBinding.tvAirDateTime.text = schedule.airDateTime
            Glide.with(context).load(schedule.show.image).centerCrop().into(itemBinding.ivImage)

            itemBinding.root.setOnClickListener { onTapListener?.onTap(schedule) }
        }
    }

    interface OnItemTap {
        fun onTap(schedule: ScheduleEntity)
    }
}
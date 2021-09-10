package com.example.tvmaze.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.databinding.ItemShowBinding

class SearchAdapter(private val shows: MutableList<ShowEntity>): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    private var onTapListener: OnItemTap? = null

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    fun updateList(_shows: List<ShowEntity>){
        shows.clear()
        shows.addAll(_shows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = shows.size

    override fun onBindViewHolder(holder: SearchHolder, position: Int) = holder.bind(shows[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(view)
    }

    inner class SearchHolder(private val itemBinding: ItemShowBinding): RecyclerView.ViewHolder(itemBinding.root){

        private val context = itemBinding.root.context

        fun bind(show: ShowEntity){
            itemBinding.tvName.text = show.name
            itemBinding.tvNetworkName.text = show.networkName
            itemBinding.tvScheduleTimeDays.text = show.scheduleTimeDays
            Glide.with(context).load(show.image).centerCrop().into(itemBinding.ivImage)

            itemBinding.root.setOnClickListener { onTapListener?.onTap(show) }
        }
    }

    interface OnItemTap {
        fun onTap(show: ShowEntity)
    }
}
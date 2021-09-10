package com.example.tvmaze.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvmaze.data.show.entity.CastEntity
import com.example.tvmaze.databinding.ItemCastBinding

class CastAdapter(private val cast: MutableList<CastEntity>): RecyclerView.Adapter<CastAdapter.CastHolder>() {

    fun updateList(_cast: List<CastEntity>){
        cast.clear()
        cast.addAll(_cast)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cast.size

    override fun onBindViewHolder(holder: CastAdapter.CastHolder, position: Int) = holder.bind(cast[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.CastHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastHolder(view)
    }

    inner class CastHolder(private val itemBinding: ItemCastBinding): RecyclerView.ViewHolder(itemBinding.root){

        private val context = itemBinding.root.context

        fun bind(cast: CastEntity){
            itemBinding.tvName.text = cast.name
            Glide.with(context).load(cast.image).centerCrop().into(itemBinding.ivImage)
        }
    }
}
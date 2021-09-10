package com.example.tvmaze.ui.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.databinding.ItemCountryBinding

class CountryAdapter(private val countries: MutableList<CountryEntity>): RecyclerView.Adapter<CountryAdapter.CountryHolder>(),
    Filterable {

    private var onTapListener: OnItemTap? = null
    private var countriesFiltered: MutableList<CountryEntity> = countries

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    fun updateList(_countries: List<CountryEntity>){
        countriesFiltered.clear()
        countriesFiltered.addAll(_countries)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val search = constraint.toString()

                if(search.isEmpty()){
                    countriesFiltered = countries
                }else{
                    val resultFiltered: MutableList<CountryEntity> = mutableListOf()

                    countries.forEach {
                        if(it.name.lowercase().contains(search.lowercase())){
                            resultFiltered.add(it)
                        }
                    }

                    countriesFiltered = resultFiltered
                }

                val filterResults = FilterResults()
                filterResults.values = countriesFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countriesFiltered = results?.values as MutableList<CountryEntity>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int = countriesFiltered.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) = holder.bind(countriesFiltered[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(view)
    }

    inner class CountryHolder(private val itemBinding: ItemCountryBinding): RecyclerView.ViewHolder(itemBinding.root){

        private val context = itemBinding.root.context

        fun bind(country: CountryEntity){
            itemBinding.tvName.text = country.name

            itemBinding.root.setOnClickListener { onTapListener?.onTap(country) }
        }
    }

    interface OnItemTap {
        fun onTap(country: CountryEntity)
    }

}
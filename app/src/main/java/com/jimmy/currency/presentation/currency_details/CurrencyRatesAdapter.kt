package com.jimmy.currency.presentation.currency_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.currency.databinding.CurrencyRateItemBinding
import com.jimmy.currency.presentation.currency_details.popular.PopularCurrency
import com.jimmy.currency.util.roundTwo

class CurrencyRatesAdapter(private val dataSet: List<PopularCurrency>) :
    RecyclerView.Adapter<CurrencyRatesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: CurrencyRateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PopularCurrency) {
            binding.apply {
                tvCurrency.text = item.key
                tvRate.text = item.value.roundTwo().toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CurrencyRateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}

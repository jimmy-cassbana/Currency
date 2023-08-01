package com.jimmy.currency.presentation.currency_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.currency.databinding.HistorySelectionItemBinding
import com.jimmy.currency.presentation.currency_details.HistoryItem
import com.jimmy.currency.util.gone
import com.jimmy.currency.util.visible

class SelectionHistoryAdapter(private val dataSet: ArrayList<HistoryItem>) :
    RecyclerView.Adapter<SelectionHistoryAdapter.ViewHolder>() {

    class ViewHolder(private val binding: HistorySelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItem) {
            binding.apply {
                item.header?.let {
                    tvDate.visible()
                    selectionCard.gone()
                    tvDate.text = it
                }

                item.body?.let {
                    selectionCard.visible()
                    tvDate.gone()
                    tvCurrencyFrom.text = it.fromCurrency
                    tvCurrencyTo.text = it.toCurrency
                    tvAmountFrom.text = it.fromValue
                    tvAmountTo.text = it.toValue
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        HistorySelectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
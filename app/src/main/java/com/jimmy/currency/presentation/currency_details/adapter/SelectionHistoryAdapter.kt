package com.jimmy.currency.presentation.currency_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.currency.databinding.HistorySelectionItemBinding
import com.jimmy.currency.presentation.currency_details.HistoryItem

class SelectionHistoryAdapter(private val dataSet: ArrayList<HistoryItem>) :
    RecyclerView.Adapter<SelectionHistoryAdapter.ViewHolder>() {

    class ViewHolder(private val binding: HistorySelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItem) {

            binding.apply {
                item.header?.let {
                    tvDate.visibility = View.VISIBLE
                    selectionCard.visibility = View.GONE
                    tvDate.text = it
                }

                item.body?.let {
                    selectionCard.visibility = View.VISIBLE
                    tvDate.visibility = View.GONE
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
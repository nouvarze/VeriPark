package com.omersakalli.veripark.presentation.imkblist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omersakalli.veripark.R
import com.omersakalli.veripark.data.model.IMKB.Stock
import com.omersakalli.veripark.databinding.ListItemBinding
import com.omersakalli.veripark.util.AES_Functions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListAdapter(val onItemListener: OnItemListener) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>(), Filterable {
    val stockList = ArrayList<Stock>()
    val stockListFull = ArrayList<Stock>()

    fun setList(stocks: List<Stock>, aesKey: String, aesIV: String) {
        stockList.clear()
        stockListFull.clear()

        stocks.forEach {
            stockList.add(
                Stock(
                    it.bid,
                    it.difference,
                    it.id,
                    it.isDown,
                    it.isUp,
                    it.offer,
                    it.price,
                    AES_Functions.decrypt(
                        it.symbol,
                        aesKey,
                        aesIV
                    ),
                    it.volume
                )
            )
        }

        stockListFull.addAll(stockList)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(stockList[position])
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else holder.itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    class MyViewHolder(
        val binding: ListItemBinding,
        val onItemListener: OnItemListener
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        fun bind(stock: Stock) {
            var darkBackground: Boolean = true

            binding.apply {
                price.text = stock.price.toString()
                difference.text = stock.difference.toString()
                bid.text = stock.bid.toString()
                offer.text = stock.offer.toString()
                volume.text = stock.volume.toString().subSequence(0, 3)
                symbol.text = stock.symbol

                if (stock.isUp) {
                    change.text = "▲"
                    change.setTextColor(Color.GREEN)
                } else if (stock.isDown) {
                    change.text = "▼"
                    change.setTextColor(Color.RED)
                } else {
                    change.text = "━"
                    change.setTextColor(Color.parseColor("#FFA500"))
                }

                listItem.setOnClickListener {
                    onClick(listItem)
                }

            }
        }

        override fun onClick(p0: View?) {
            CoroutineScope(Dispatchers.Main).launch {
                onItemListener.onItemClick(adapterPosition)
            }
        }


    }

    interface OnItemListener {
        suspend fun onItemClick(position: Int)
    }

    override fun getFilter(): Filter {
        return stockFilter
    }

    private val stockFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<Stock>()
            if (constraint == null || constraint.length == 0) filteredList.addAll(stockListFull)
            else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                stockListFull.forEach {
                    if (it.symbol.toLowerCase().contains(filterPattern))
                        filteredList.add(it)
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            stockList.clear()
            stockList.addAll(results!!.values as ArrayList<Stock>)
            notifyDataSetChanged()
        }

    }
}


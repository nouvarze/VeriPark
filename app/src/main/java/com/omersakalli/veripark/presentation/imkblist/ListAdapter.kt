package com.omersakalli.veripark.presentation.imkblist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omersakalli.veripark.R
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.model.IMKB.Stock
import com.omersakalli.veripark.databinding.ListItemBinding
import com.omersakalli.veripark.util.AES_Functions
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter() :
    RecyclerView.Adapter<MyViewHolder>() {
    private val stockList = ArrayList<Stock>()
    private var aesKey = ""
    private var aesIV = ""

    fun setList(stocks: List<Stock>, aesKey: String, aesIV: String) {
        stockList.clear()
        stockList.addAll(stocks)
        this.aesKey = aesKey
        this.aesIV = aesIV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding, aesKey, aesIV)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(stockList[position])
        if(position%2 == 0){
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
        else holder.itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

}

class MyViewHolder(val binding: ListItemBinding, val aesKey: String, val aesIV: String) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(stock: Stock) {
        var darkBackground:Boolean = true
        if (aesKey != "")
            binding.apply {/*
                if(darkBackground) {
                    listItem.setBackgroundColor(Color.LTGRAY)
                }
                darkBackground = darkBackground.not()*/
                symbol.text = AES_Functions.decrypt(
                    stock.symbol,
                    aesKey,
                    aesIV
                )
                price.text = stock.price.toString()
                difference.text = stock.difference.toString()
                bid.text = stock.bid.toString()
                offer.text = stock.offer.toString()
                volume.text = stock.volume.toString().subSequence(0,3)

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

            }

    }
}
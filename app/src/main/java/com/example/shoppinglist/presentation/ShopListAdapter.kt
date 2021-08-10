package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopitemViewHolder>() {

    var shopList = listOf<ShopItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopitemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false)
        return ShopitemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopitemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) {
            "Active"
        }else {
            "Not active"
        }

        viewHolder.view.setOnLongClickListener {
            true
        }
        if (shopItem.enabled) {

            viewHolder.tvName.text = "${shopItem.name} $status"
            viewHolder.tvCount.text = shopItem.count.toString()

            viewHolder.tvName.setTextColor(ContextCompat.getColor(
                viewHolder.view.context,
                android.R.color.holo_red_light
            ))
        }
    }

    override fun onViewRecycled(viewHolder: ShopitemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""

        viewHolder.tvName.setTextColor(ContextCompat.getColor(
            viewHolder.view.context,
            android.R.color.white
        ))
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return position
//    }

    class ShopitemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

}
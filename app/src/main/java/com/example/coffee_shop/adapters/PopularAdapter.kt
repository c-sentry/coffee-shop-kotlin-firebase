package com.example.coffee_shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffee_shop.databinding.ViewholderPopularBinding
import com.example.coffee_shop.domin.ItemModel

class PopularAdapter(val items: MutableList<ItemModel>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderPopularBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleText.text = items[position].title
        holder.binding.subTitleText.text = items[position].extra
        holder.binding.priceText.text = items[position].price.toString()

        Glide.with(context).load(items[position].picUrl[0]).into(holder.binding.pic)

    }

    override fun getItemCount(): Int = items.size
}
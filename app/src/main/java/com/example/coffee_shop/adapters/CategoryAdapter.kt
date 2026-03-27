package com.example.coffee_shop.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_shop.R
import com.example.coffee_shop.activities.ItemListActivity
import com.example.coffee_shop.databinding.ViewholderCategoryBinding
import com.example.coffee_shop.domin.CategoryModel

class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastPosition = -1

    class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener {
            lastPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastPosition)
            notifyItemChanged(selectedPosition)
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(context, ItemListActivity::class.java).apply {
                    putExtra("title", item.title)
                    putExtra("id", item.id.toString())
                }
                ContextCompat.startActivity(context, intent, null)
            }, 500)
        }
        if (selectedPosition == position) {
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_circle)
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_2_circle)
        }
    }

    override fun getItemCount(): Int = items.size

}


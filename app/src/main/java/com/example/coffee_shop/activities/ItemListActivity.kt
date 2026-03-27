package com.example.coffee_shop.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_shop.R
import com.example.coffee_shop.adapters.PopularAdapter
import com.example.coffee_shop.databinding.ActivityItemListBinding
import com.example.coffee_shop.databinding.ActivitySplashBinding
import com.example.coffee_shop.viewModel.MainViewModel

class ItemListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemListBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.loadCategoryItems(id).observe(this@ItemListActivity, Observer {
                listView.layoutManager = GridLayoutManager(this@ItemListActivity, 2)
                listView.adapter = PopularAdapter(it)
                progressBar.visibility = View.GONE

            })
            backButton.setOnClickListener { finish() }
        }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""

        binding.categoryText.text = title
    }
}
package com.example.coffee_shop.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffee_shop.adapters.CategoryAdapter
import com.example.coffee_shop.adapters.PopularAdapter
import com.example.coffee_shop.databinding.ActivityMainBinding
import com.example.coffee_shop.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initPopular()
    }

    private fun initCategory() {
        binding.apply {
            progressBarCategory.visibility = View.VISIBLE
            viewModel.loadCategory().observeForever {
                categoryView.adapter = CategoryAdapter(it)
                categoryView.layoutManager = LinearLayoutManager(
                    this@MainActivity, LinearLayoutManager.HORIZONTAL, false
                )
                progressBarCategory.visibility = View.GONE

            }
            viewModel.loadCategory()

        }
    }

    private fun initPopular() {
        binding.apply {
            progressBarPopular.visibility = View.VISIBLE
            viewModel.loadPopular().observeForever {
                papularView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                papularView.adapter = PopularAdapter(it)
                progressBarPopular.visibility = View.GONE
            }
            viewModel.loadPopular()
        }
    }

    private fun initBanner() {
        binding.apply {
            progressBarBanner.visibility = View.VISIBLE
            viewModel.loadBanner().observeForever {
                Glide.with(this@MainActivity).load(it[0].url).into(banner)
                progressBarBanner.visibility = View.GONE
            }
            viewModel.loadBanner()
        }
    }


}
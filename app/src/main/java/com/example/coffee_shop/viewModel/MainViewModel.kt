package com.example.coffee_shop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffee_shop.domin.BannerModel
import com.example.coffee_shop.domin.CategoryModel
import com.example.coffee_shop.repository.MainRepository

class MainViewModel: ViewModel() {
    private  val repository = MainRepository()
    fun  loadBanner(): LiveData<MutableList<BannerModel>>{
      return  repository.loadBanner()
    }
    fun  loadCategory(): LiveData<MutableList<CategoryModel>>{
      return  repository.loadCategory()
    }
}
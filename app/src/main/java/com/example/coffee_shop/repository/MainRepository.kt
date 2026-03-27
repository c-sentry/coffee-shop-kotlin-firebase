package com.example.coffee_shop.repository

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffee_shop.R
import com.example.coffee_shop.domin.BannerModel
import com.example.coffee_shop.domin.CategoryModel
import com.example.coffee_shop.domin.ItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        val listData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (snapshotItem in snapshot.children) {
                    val item = snapshotItem.getValue(BannerModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return listData
    }


    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (snapshotItem in snapshot.children) {
                    val item = snapshotItem.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return listData
    }

    fun loadPopular(): LiveData<MutableList<ItemModel>> {
        val listData = MutableLiveData<MutableList<ItemModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemModel>()
                for (snapshotItem in snapshot.children) {
                    val item = snapshotItem.getValue(ItemModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return listData
    }

    fun loadCategoryItem(categoryId:  String = ""): LiveData<MutableList<ItemModel>>{
        val itemLiveData = MutableLiveData<MutableList<ItemModel>>()
        val ref = firebaseDatabase.getReference("Items")
        val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemModel>()
                for(childrenSnapshot in snapshot.children){
                    val item = childrenSnapshot.getValue(ItemModel::class.java)
                    item?.let { list.add(it) }
                }
                itemLiveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return  itemLiveData

    }

}
package com.example.coffee_shop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffee_shop.domin.BannerModel
import com.example.coffee_shop.domin.CategoryModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private  val firebaseDatabase = FirebaseDatabase.getInstance()
    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        val listData = MutableLiveData<MutableList<BannerModel>> ()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (snapshotItem in snapshot.children){
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


    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        val listData = MutableLiveData<MutableList<CategoryModel>> ()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (snapshotItem in snapshot.children){
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

}
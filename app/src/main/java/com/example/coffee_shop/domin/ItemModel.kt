package com.example.coffee_shop.domin

import java.io.Serializable

data class ItemModel(
    var title: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var description: String = "",
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var extra: String = ""
) : Serializable

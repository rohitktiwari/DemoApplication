package com.triazine.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rohit on 30-05-2020.
 */
@Entity
class Product(
    @PrimaryKey val id: Int,
    val name: String?,
    val desc: String?,
    val regPrice: Int?,
    val salePrice: Int?,
    val productPhoto: String?,
    val colors: List<String>
)
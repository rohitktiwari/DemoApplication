package com.triazine.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Rohit on 31-05-2020.
 */

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCT")
    suspend fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg product: Product)

    @Delete
    suspend fun delete(product: Product)


}
package com.triazine.myapplication.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.triazine.myapplication.adapters.CreateProductAdapter
import com.triazine.myapplication.Product
import com.triazine.myapplication.ProductDatabase
import com.triazine.myapplication.R
import kotlinx.android.synthetic.main.create_product.*
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Created by Rohit on 30-05-2020.
 */

class CreateProductActivity : AppCompatActivity() {

    private lateinit var products: ArrayList<Product>
    private lateinit var adapter: CreateProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_product)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Create Product"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        val jsonFileString = getJsonDataFromAsset(applicationContext)
        Log.i("data", jsonFileString)

        val gson = Gson()
        val listProductType = object : TypeToken<List<Product>>() {}.type

        products = gson.fromJson(jsonFileString, listProductType)
        rvCreateProductList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CreateProductAdapter(
            products,
            this
        )
        rvCreateProductList.adapter = adapter
    }

    fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString =
                context.assets.open("productJson.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.stay
        )
    }

    fun insertProduct(product: Product, adapterPosition: Int) {
        runBlocking {
            ProductDatabase.getDatabase(this@CreateProductActivity)
                .productDao().insertAll(product)
        }
        products.remove(product)
        adapter.notifyItemRemoved(adapterPosition)
        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show()
        if(products.size==0){
            finish()
        }
    }
}
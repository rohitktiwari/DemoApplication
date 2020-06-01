package com.triazine.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.triazine.myapplication.Product
import com.triazine.myapplication.ProductDatabase
import com.triazine.myapplication.R
import com.triazine.myapplication.adapters.ShowProductAdapter
import kotlinx.android.synthetic.main.show_product.*
import kotlinx.coroutines.runBlocking

/**
 * Created by Rohit on 30-05-2020.
 */
class ShowProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_product)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Show Product"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        var products: List<Product> = listOf()

        products =
            runBlocking { ProductDatabase.getDatabase(
                applicationContext
            ).productDao().getAll() }

        rv_showProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = ShowProductAdapter(
            ArrayList(products),
            this
        )
        rv_showProduct.adapter = adapter
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
}
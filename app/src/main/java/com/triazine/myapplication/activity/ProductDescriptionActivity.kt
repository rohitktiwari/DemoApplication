package com.triazine.myapplication.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triazine.myapplication.Product
import com.triazine.myapplication.ProductDatabase
import com.triazine.myapplication.R
import kotlinx.android.synthetic.main.product_desc.*
import kotlinx.coroutines.runBlocking

/**
 * Created by Rohit on 01-06-2020.
 */

class ProductDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_desc)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Product Details"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        val products: List<Product>

        products =
            runBlocking { ProductDatabase.getDatabase(
                applicationContext
            ).productDao().getAll() }

        val position = intent.getIntExtra("position", -1);

        Glide.with(this).load(products.get(position).productPhoto).into(iv_productphoto);
        tv_name.text = products.get(position).name
        tv_desc.text = products.get(position).desc
        tv_salePrice.text = "\u20B9 " + products.get(position).salePrice.toString()
        tv_regPrice.text = products.get(position).regPrice.toString()
        tv_regPrice.paintFlags = tv_regPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        iv_productphoto.setOnClickListener {
            val intent = Intent(this@ProductDescriptionActivity,
                ImageViewActivity::class.java)
            intent.putExtra("image",products.get(position).productPhoto)
            startActivity(intent)
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
        }

        deleteBtn.setOnClickListener {
            runBlocking {
                ProductDatabase.getDatabase(
                    applicationContext
                ).productDao()
                    .delete(products.get(position))
            }
            startActivity(Intent(this@ProductDescriptionActivity,
                ShowProductActivity::class.java))
            finish()
        }

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
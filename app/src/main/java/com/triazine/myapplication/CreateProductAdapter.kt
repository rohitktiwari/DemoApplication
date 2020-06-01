package com.triazine.myapplication

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by Rohit on 31-05-2020.
 */

class CreateProductAdapter(
    private val productList: ArrayList<Product>, private val context: CreateProductActivity
) :
    RecyclerView.Adapter<CreateProductAdapter.CreateProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateProductViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return CreateProductViewHolder(itemView, context)
    }

    class CreateProductViewHolder(itemView: View, val context: CreateProductActivity) :
        RecyclerView.ViewHolder(itemView) {
        fun bindItems(product: Product) {
            val tv_name = itemView.findViewById(R.id.tv_name) as TextView
            val tv_desc = itemView.findViewById(R.id.tv_desc) as TextView
            val tv_saleprice = itemView.findViewById(R.id.tv_salePrice) as TextView
            val tv_regprice = itemView.findViewById(R.id.tv_regPrice) as TextView
            val iv_photo = itemView.findViewById(R.id.ivPhoto) as ImageView
            tv_name.text = product.name
            tv_desc.text = product.desc
            tv_saleprice.text = "\u20B9 " + product.salePrice.toString()
            tv_regprice.text = product.regPrice.toString()
            tv_regprice.paintFlags = tv_regprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            Glide.with(itemView.context).load(product.productPhoto).into(iv_photo)
            val cv_product = itemView.findViewById(R.id.cv_product) as CardView
            cv_product.setOnClickListener {

            }
            val btnAdd = itemView.findViewById(R.id.addProduct) as ImageView

            btnAdd.setOnClickListener {
               context.insertProduct(product,adapterPosition)
            }

            for(color in product.colors){
                val imageView = ImageView(context)
                // setting height and width of imageview
                imageView.layoutParams = LinearLayout.LayoutParams(35, 35)
                val param = imageView.layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(10,10,10,10)
                imageView.layoutParams = param

                imageView.setBackgroundColor(Color.parseColor(color))
                imageView.setPadding(10,10,10,10)
                val layout = itemView.findViewById(R.id.ll_colors) as LinearLayout
                // Add ImageView to LinearLayout
                layout.addView(imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CreateProductViewHolder, position: Int) {
        holder.bindItems(productList.get(position))
    }
}

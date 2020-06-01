package com.triazine.myapplication

import android.content.Intent
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
 * Created by Rohit on 01-06-2020.
 */

class ShowProductAdapter(
    private val productList: ArrayList<Product>, private val activity: ShowProductActivity
) :
    RecyclerView.Adapter<ShowProductAdapter.ShowProductViewHolder>() {
    class ShowProductViewHolder(itemView: View, val activity: ShowProductActivity) :
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
                val intent = Intent(activity, ProductDescriptionActivity::class.java)
                intent.putExtra("position", adapterPosition)
                activity.startActivity(intent)
                activity.overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
            }
            val btnAdd = itemView.findViewById(R.id.addProduct) as ImageView
            btnAdd.visibility = View.INVISIBLE

            for (color in product.colors) {
                val imageView = ImageView(activity)
                // setting height and width of imageview
                imageView.layoutParams = LinearLayout.LayoutParams(35, 35)
                val param = imageView.layoutParams as ViewGroup.MarginLayoutParams
                //setting margins for ImageViews
                param.setMargins(10, 10, 10, 10)
                imageView.layoutParams = param

                imageView.setBackgroundColor(Color.parseColor(color))
                imageView.setPadding(10, 10, 10, 10)
                val layout = itemView.findViewById(R.id.ll_colors) as LinearLayout
                // Add ImageView to LinearLayout
                layout.addView(imageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ShowProductViewHolder(itemView, activity)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ShowProductViewHolder, position: Int) {
        holder.bindItems(productList.get(position))
    }

}
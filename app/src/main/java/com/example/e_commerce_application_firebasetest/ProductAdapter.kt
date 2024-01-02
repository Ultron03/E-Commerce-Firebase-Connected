package com.example.e_commerce_application_firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Context
import android.util.Log
import android.widget.Toast

class ProductAdapter(
    private val listOfProduct:List<Product>,
    private val context:Context

) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val product_img:ImageView = itemView.findViewById(R.id.product_img)
        val product_name: TextView = itemView.findViewById(R.id.prodct_name)
        val product_price: TextView = itemView.findViewById(R.id.product_price)
        val product_des: TextView = itemView.findViewById(R.id.prodct_des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_card,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = listOfProduct[position]
        holder.product_name.text = currentProduct.product_name
        holder.product_price.text = currentProduct.product_price
        holder.product_des.text = currentProduct.product_des
        Glide.with(context)
            .load(currentProduct.product_image)
            .into(holder.product_img)
    }

}
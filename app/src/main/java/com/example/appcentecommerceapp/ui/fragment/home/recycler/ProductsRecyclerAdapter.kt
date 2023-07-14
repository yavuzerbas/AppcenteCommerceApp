package com.example.appcentecommerceapp.ui.fragment.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.ItemHomeProductBinding

class ProductsRecyclerAdapter(
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener<ProductResponse?>
) : RecyclerView.Adapter<ProductsViewHolder>() {
    private var products : ArrayList<ProductResponse>? = null

    fun setProducts(products: ArrayList<ProductResponse>?){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemHomeProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(binding,recyclerViewItemClickListener)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        return holder.bind(products?.get(position))
    }
}
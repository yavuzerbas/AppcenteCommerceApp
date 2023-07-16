package com.example.appcentecommerceapp.ui.fragment.shoppingcart.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemRemoveClickListener
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.ItemShoppingCartBinding

class ShoppingCartRecyclerAdapter(
    private val recyclerViewItemRemoveClickListener: RecyclerViewItemRemoveClickListener<ProductResponse?>
) : RecyclerView.Adapter<ShoppingCartViewHolder>() {

    private var products: MutableList<ProductResponse>? = null

    fun setProducts(products: MutableList<ProductResponse>?) {
        this.products = products//?.toMutableList()
        notifyDataSetChanged()
    }
    fun removedProduct(index : Int){
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val binding = ItemShoppingCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingCartViewHolder(binding, recyclerViewItemRemoveClickListener)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        return holder.bind(products?.get(position))
    }
}

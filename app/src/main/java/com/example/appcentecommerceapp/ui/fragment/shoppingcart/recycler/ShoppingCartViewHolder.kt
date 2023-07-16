package com.example.appcentecommerceapp.ui.fragment.shoppingcart.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemRemoveClickListener
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.data.utils.extensions.loadImage
import com.example.appcentecommerceapp.databinding.ItemShoppingCartBinding

class ShoppingCartViewHolder(
    private val binding : ItemShoppingCartBinding,
    private val recyclerViewItemRemoveClickListener: RecyclerViewItemRemoveClickListener<ProductResponse?>
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProductResponse?){

        binding.ivItem.loadImage(item?.productImage)
        binding.tvItemName.text = item?.productName
        binding.tvItemPrice.text = item?.newPrice.toString()

        binding.btnCancel.setOnClickListener{
            recyclerViewItemRemoveClickListener.onRemoveClick(item)
        }
    }
}
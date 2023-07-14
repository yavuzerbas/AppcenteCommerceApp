package com.example.appcentecommerceapp.ui.fragment.home.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.data.utils.extensions.loadImage
import com.example.appcentecommerceapp.databinding.ItemHomeProductBinding

class ProductsViewHolder(
    private val binding : ItemHomeProductBinding,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener<ProductResponse?>
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProductResponse?){
        binding.ivProduct.loadImage(item?.productImage)
        binding.tvProductName.text = item?.productName
        binding.tvProductType.text = item?.type
        binding.tvProductOldPrice.text = item?.oldPrice.toString()//"$\${item?.oldPrice}"
        binding.tvProductNewPrice.text = item?.newPrice.toString()

        binding.root.setOnClickListener{
            recyclerViewItemClickListener.onClick(item)
        }

    }
}
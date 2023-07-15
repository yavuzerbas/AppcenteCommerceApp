package com.example.appcentecommerceapp.ui.fragment.productdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.data.utils.extensions.loadImage
import com.example.appcentecommerceapp.databinding.FragmentProductDetailBinding


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {

    private val args : ProductDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUi()
    }

    private fun prepareUi() {
        args.product?.let {
            binding?.ivProduct?.loadImage(it.productImage)
            binding?.tvProductName?.text = it.productName
            binding?.tvProductPrice?.text = it.newPrice.toString()
        }
    }
}
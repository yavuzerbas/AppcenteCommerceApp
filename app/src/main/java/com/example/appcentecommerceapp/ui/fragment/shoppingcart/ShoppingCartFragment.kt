package com.example.appcentecommerceapp.ui.fragment.shoppingcart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcentecommerceapp.R
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding>(FragmentShoppingCartBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }
}
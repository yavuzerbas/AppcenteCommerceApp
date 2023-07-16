package com.example.appcentecommerceapp.ui.fragment.shoppingcart

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemRemoveClickListener
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.FragmentShoppingCartBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import com.example.appcentecommerceapp.ui.fragment.shoppingcart.recycler.ShoppingCartRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding>(FragmentShoppingCartBinding::inflate) {
    private lateinit var shoppingCartRecyclerAdapter: ShoppingCartRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareShoppingCartAdapter()
        getShoppingCartProducts()
    }

    private fun prepareShoppingCartAdapter() {
        shoppingCartRecyclerAdapter = ShoppingCartRecyclerAdapter(recyclerViewItemRemoveClickListener)
        binding?.rvShoppingCart?.layoutManager = LinearLayoutManager(context)
        binding?.rvShoppingCart?.adapter = shoppingCartRecyclerAdapter
    }

    private val recyclerViewItemRemoveClickListener = object : RecyclerViewItemRemoveClickListener<ProductResponse?>{
        override fun onRemoveClick(item: ProductResponse?) {
            item?.let {
                shoppingCartRecyclerAdapter.removeProduct(it)
            }
        }

    }
    private fun getShoppingCartProducts(){
        NetworkHelper.cartService.getShoppingCartProducts()
            .enqueue(object : Callback<BaseResponse<CartResponse?>> {
                override fun onResponse(
                    call: Call<BaseResponse<CartResponse?>>,
                    response: Response<BaseResponse<CartResponse?>>
                ) {
                    when{
                        response.isSuccessful -> {
                            shoppingCartRecyclerAdapter.setProducts(response.body()?.result?.products)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
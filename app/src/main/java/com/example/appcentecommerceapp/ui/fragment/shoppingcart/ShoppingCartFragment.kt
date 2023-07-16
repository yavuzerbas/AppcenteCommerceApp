package com.example.appcentecommerceapp.ui.fragment.shoppingcart

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemRemoveClickListener
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.data.utils.extensions.setPrice
import com.example.appcentecommerceapp.databinding.FragmentShoppingCartBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import com.example.appcentecommerceapp.ui.fragment.shoppingcart.recycler.ShoppingCartRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding>(FragmentShoppingCartBinding::inflate) {
    private lateinit var shoppingCartRecyclerAdapter: ShoppingCartRecyclerAdapter
    private var products : MutableList<ProductResponse>? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareShoppingCartAdapter()
        prepareUI()
        buyButtonOnClickListener()
        getShoppingCartProducts()
    }

    private fun prepareUI(){
        setTotalPrice()
        handleBuyButtonBehaviour()
    }

    private fun handleBuyButtonBehaviour() {
        //TODO("Not yet implemented")
    }

    private fun setTotalPrice() {
        binding?.tvPrice?.setPrice(calculateTotalPrice())
    }

    private fun calculateTotalPrice(): String {
        var totalPrice : Double = 0.0
        val currentProducts = products
        if(!currentProducts.isNullOrEmpty()){
            currentProducts.forEach{
                totalPrice += it.newPrice ?: 0.0
            }
        }
        return totalPrice.toString()
    }


    private fun prepareShoppingCartAdapter() {
        shoppingCartRecyclerAdapter = ShoppingCartRecyclerAdapter(recyclerViewItemRemoveClickListener)
        binding?.rvShoppingCart?.layoutManager = LinearLayoutManager(context)
        binding?.rvShoppingCart?.adapter = shoppingCartRecyclerAdapter
    }

    private val recyclerViewItemRemoveClickListener = object : RecyclerViewItemRemoveClickListener<ProductResponse?>{
        override fun onRemoveClick(item: ProductResponse?) {
            item?.let {
                //shoppingCartRecyclerAdapter.removeProduct(it)
                removeProductFromShoppingCart(it)
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
                            products = response.body()?.result?.products?.toMutableList()
                            shoppingCartRecyclerAdapter.setProducts(products)
                            prepareUI()
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun removeProductFromShoppingCart(productResponse: ProductResponse){
        NetworkHelper.cartService.removeProductFromShoppingCart(productResponse.id.toString())
            .enqueue(object : Callback<BaseResponse<CartResponse?>>{
                override fun onResponse(
                    call: Call<BaseResponse<CartResponse?>>,
                    response: Response<BaseResponse<CartResponse?>>
                ) {
                    when{
                        response.isSuccessful ->{
                            val index = removeProduct(productResponse)
                            shoppingCartRecyclerAdapter.removedProduct(index)
                            prepareUI()
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun removeProduct(product: ProductResponse): Int {
        var index : Int = -1
        products?.let {
            index = it.indexOf(product)
            if (index != -1) {
                it.removeAt(index)
                //notifyItemRemoved(index)
            }
        }
        return index
    }
    private fun buyButtonOnClickListener(){
        binding?.btnBuy?.setOnClickListener {
            buyProducts()
        }
    }
    private fun buyProducts(){
        NetworkHelper.cartService.clearCart()
            .enqueue(object : Callback<BaseResponse<CartResponse?>>{
                override fun onResponse(
                    call: Call<BaseResponse<CartResponse?>>,
                    response: Response<BaseResponse<CartResponse?>>
                ) {
                    when{
                        response.isSuccessful -> {
                            removeAllProducts()
                            prepareUI()
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun removeAllProducts(){
        products?.clear()
        binding?.tvPrice?.setPrice("0")
        shoppingCartRecyclerAdapter.removedAllProduct()
    }

}
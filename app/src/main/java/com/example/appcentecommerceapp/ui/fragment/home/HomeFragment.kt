package com.example.appcentecommerceapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CurrentStore
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.FragmentHomeBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import com.example.appcentecommerceapp.ui.fragment.home.recycler.ProductsRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var productsRecyclerAdapter: ProductsRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareProductsRecyclerAdapter()
        getHomePageProducts()

    }

    private fun prepareProductsRecyclerAdapter(){
        productsRecyclerAdapter = ProductsRecyclerAdapter(recyclerViewItemClickListener)
        binding?.rcProducts?.layoutManager = GridLayoutManager(context,2)
        binding?.rcProducts?.adapter = productsRecyclerAdapter
    }

    private val recyclerViewItemClickListener = object : RecyclerViewItemClickListener<ProductResponse?>{
        override fun onClick(item: ProductResponse?) {
            navigateToProductDetailFragment(item)
        }
    }

    private fun navigateToProductDetailFragment(productResponse: ProductResponse?) {
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToProductDetailFragment(productResponse)
        )
    }

    private fun getHomePageProducts() {
        NetworkHelper.productService.getHomePageProducts()
            .enqueue(object : Callback<BaseResponse<List<ProductResponse>?>>{
                override fun onResponse(
                    call: Call<BaseResponse<List<ProductResponse>?>>,
                    response: Response<BaseResponse<List<ProductResponse>?>>
                ) {
                    when{
                        response.isSuccessful ->{
                            var products = response.body()?.result
                            products?.forEach{
                                it.currentStores = listOf(
                                    CurrentStore(39.9208, 32.8541), // Kocatepe Cami
                                    CurrentStore(39.9262, 32.8369), // Anıtkabir
                                    CurrentStore(39.9415, 32.8597)  // Ankara Kalesi
                                )
                            }
                            //productsRecyclerAdapter.setProducts(response.body()?.result)
                            productsRecyclerAdapter.setProducts(products)
                        }
                    }
                }
                override fun onFailure(
                    call: Call<BaseResponse<List<ProductResponse>?>>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}


package com.example.appcentecommerceapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.FragmentHomeBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import com.example.appcentecommerceapp.ui.fragment.home.recycler.ProductsRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    // Create a reference to the adapter
    /*private val adapter = ProductsRecyclerAdapter(object:
        RecyclerViewItemClickListener<ProductResponse?> {
        override fun onClick(item: ProductResponse?) {

        }
    })*/
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
                            productsRecyclerAdapter.setProducts(response.body()?.result)
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


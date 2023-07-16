package com.example.appcentecommerceapp.ui.fragment.productdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import com.example.appcentecommerceapp.data.model.reponse.CurrentStore
import com.example.appcentecommerceapp.data.utils.extensions.loadImage
import com.example.appcentecommerceapp.databinding.FragmentProductDetailBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {

    private val args : ProductDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUi()
        whichStoresButtonOnClick()
        backButtonOnClick()
        addCartButtonOnClick()
    }

    private fun prepareUi() {
        args.product?.let {
            binding?.ivProduct?.loadImage(it.productImage)
            binding?.tvProductName?.text = it.productName
            binding?.tvProductPrice?.text = it.newPrice.toString()
        }
    }

    private fun backButtonOnClick() {
        //btn_back in main activity click handle
    }
    private fun whichStoresButtonOnClick(){
        binding?.btnWhichStore?.setOnClickListener {
            args.product?.currentStores?.let { currentStores ->
                navigateToWhichStoreMapFragment(
                    currentStores
                )
            }
        }
    }
    private fun navigateToWhichStoreMapFragment(currentStores : List<CurrentStore>){
        findNavController().navigate(ProductDetailFragmentDirections
            .actionProductDetailFragmentToWhichStoreMapFragment(
                currentStores.toTypedArray()
            )
        )
    }
    private fun addCartButtonOnClick(){
        binding?.btnAddCart?.setOnClickListener {
            args.product?.id?.let {
                addProductToShoppingCart(it.toString())
            }
        }
    }
    private fun addProductToShoppingCart(id: String){
        NetworkHelper.cartService.addProductToShoppingCart(id.toString())
            .enqueue(object : Callback<BaseResponse<CartResponse?>> {
                override fun onResponse(
                    call: Call<BaseResponse<CartResponse?>>,
                    response: Response<BaseResponse<CartResponse?>>
                ) {
                    when{
                        response.isSuccessful ->{

                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
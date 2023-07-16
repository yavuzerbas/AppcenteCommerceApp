package com.example.appcentecommerceapp.network.service

import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartService {
    @GET("cart/2")
    fun getShoppingCartProducts() : Call<BaseResponse<CartResponse?>>

}
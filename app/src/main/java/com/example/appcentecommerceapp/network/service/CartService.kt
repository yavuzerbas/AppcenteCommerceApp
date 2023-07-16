package com.example.appcentecommerceapp.network.service

import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {
    @GET("cart/2")
    fun getShoppingCartProducts() : Call<BaseResponse<CartResponse?>>
    @POST("addtocart/2/{id}")
    fun addProductToShoppingCart(@Path("id") id: String): Call<BaseResponse<CartResponse?>>

    @GET("removeproduct/2/{id}")
    fun removeProductFromShoppingCart(@Path("id") id: String) : Call<BaseResponse<CartResponse?>>

    @GET("clearcart/2")
    fun clearCart() : Call<BaseResponse<CartResponse?>>

}
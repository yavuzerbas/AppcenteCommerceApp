package com.example.appcentecommerceapp.network.service

import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    fun getHomePageProducts() : Call<BaseResponse<ProductResponse>?>

}
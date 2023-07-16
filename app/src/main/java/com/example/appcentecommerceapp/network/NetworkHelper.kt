package com.example.appcentecommerceapp.network

import com.example.appcentecommerceapp.BuildConfig
import com.example.appcentecommerceapp.network.service.CartService
import com.example.appcentecommerceapp.network.service.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkHelper {
    private lateinit var retrofit: Retrofit

    lateinit var productService: ProductService
    lateinit var cartService: CartService

    init {
        retrofitBuilder()
        bindServices()
    }
    private fun retrofitBuilder() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun bindServices() {
        productService = retrofit.create(ProductService::class.java)
        cartService = retrofit.create(CartService::class.java)
    }
    private fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        //httpClient.addInterceptor(createHttpLoggingInterceptor())

        return httpClient.build()
    }
}
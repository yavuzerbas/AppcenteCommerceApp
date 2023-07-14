package com.example.appcentecommerceapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcentecommerceapp.R
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.network.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var products : ArrayList<ProductResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchHomePageProducts()
    }
    fun fetchHomePageProducts() {
        NetworkHelper.productService.getHomePageProducts().enqueue(object :
            Callback<BaseResponse<ProductResponse>?> {
            override fun onResponse(
                call: Call<BaseResponse<ProductResponse>?>,
                response: Response<BaseResponse<ProductResponse>?>
            ) {
                if (response.isSuccessful) {
                    val fetchedProducts = response.body()?.result

                } else {
                    // TODO handle error
                }
            }
            override fun onFailure(call: Call<BaseResponse<ProductResponse>?>, t: Throwable) {
                // TODO handle failure
            }
        })

    }
}
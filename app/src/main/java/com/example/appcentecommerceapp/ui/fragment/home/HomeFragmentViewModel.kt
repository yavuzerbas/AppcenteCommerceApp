package com.example.appcentecommerceapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.network.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {
    // LiveData to hold the fetched products
    private val _products = MutableLiveData<List<ProductResponse>?>()
    val products: LiveData<List<ProductResponse>?> = _products

    fun fetchHomePageProducts() {
        NetworkHelper.productService.getHomePageProducts().enqueue(object :
            Callback<BaseResponse<ProductResponse>?> {
            override fun onResponse(
                call: Call<BaseResponse<ProductResponse>?>,
                response: Response<BaseResponse<ProductResponse>?>
            ) {
                if (response.isSuccessful) {
                    val fetchedProducts = response.body()?.result
                    _products.value = fetchedProducts  // Assign fetched products to _products MutableLiveData
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
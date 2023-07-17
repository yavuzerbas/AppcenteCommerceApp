package com.example.appcentecommerceapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcentecommerceapp.R

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.appcentecommerceapp.base.model.BaseResponse
import com.example.appcentecommerceapp.base.notifier.CartStatusNotifier
import com.example.appcentecommerceapp.data.model.reponse.CartResponse
import com.example.appcentecommerceapp.databinding.ActivityMainBinding
import com.example.appcentecommerceapp.network.NetworkHelper
import com.example.appcentecommerceapp.ui.fragment.dialog.ReturnHomeDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),CartStatusNotifier,ReturnHomeDialogFragment.ReturnHomeListener {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleBackButtonVisibility()
        setupNavigationHeaderButtons()
        handleShoppingCartStatusVisibility()
    }

    private fun handleBackButtonVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                binding.btnBack.visibility = View.INVISIBLE
            } else {
                binding.btnBack.visibility = View.VISIBLE
            }
        }
    }

    private fun setupNavigationHeaderButtons() {
        binding.btnBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.btnShopping.setOnClickListener {
            if (navController.currentDestination?.id != R.id.shoppingCartFragment) {
                navController.navigate(R.id.shoppingCartFragment)
            }
        }
    }
    private fun handleShoppingCartStatusVisibility(){
        NetworkHelper.cartService.getShoppingCartProducts()
            .enqueue(object : Callback<BaseResponse<CartResponse?>> {
                override fun onResponse(
                    call: Call<BaseResponse<CartResponse?>>,
                    response: Response<BaseResponse<CartResponse?>>
                ) {
                    when{
                        response.isSuccessful -> {
                            if(response.body()?.result?.products.isNullOrEmpty()){
                                binding.viewShoppingCartStatus.visibility = View.INVISIBLE
                            }
                            else{
                                binding.viewShoppingCartStatus.visibility = View.VISIBLE
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<CartResponse?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun updateCartStatus(isEmpty: Boolean) {
        binding.viewShoppingCartStatus.visibility = if (isEmpty) View.INVISIBLE else View.VISIBLE
    }

    override fun onReturnHome() {
        val navController = findNavController(R.id.nav_graph_main)
        navController.navigate(R.id.homeFragment)
    }
}
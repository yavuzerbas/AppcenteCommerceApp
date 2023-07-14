package com.example.appcentecommerceapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentecommerceapp.data.model.reponse.ProductResponse
import com.example.appcentecommerceapp.databinding.FragmentHomeBinding
import com.example.appcentecommerceapp.ui.fragment.home.recycler.ProductsRecyclerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()

    // Create a reference to the adapter
    private val adapter = ProductsRecyclerAdapter(object:
        RecyclerViewItemClickListener<ProductResponse?> {
        override fun onClick(item: ProductResponse?) {
            TODO("Not yet implemented")
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView
        binding?.rcProducts?.layoutManager = GridLayoutManager(context,2)
        binding?.rcProducts?.adapter = adapter

        viewModel.fetchHomePageProducts()

        viewModel.products.observe(viewLifecycleOwner) { products ->
            // Update the RecyclerView with the fetched products
            adapter.setProducts(products as ArrayList<ProductResponse>?)
        }
    }
}


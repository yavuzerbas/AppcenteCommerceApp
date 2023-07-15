package com.example.appcentecommerceapp.ui.fragment.whichstore

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.appcentecommerceapp.base.fragment.BaseFragment
import com.example.appcentecommerceapp.databinding.FragmentWhichStoreMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class WhichStoreMapFragment : BaseFragment<FragmentWhichStoreMapBinding>(FragmentWhichStoreMapBinding::inflate),OnMapReadyCallback {

    private val args : WhichStoreMapFragmentArgs by navArgs()
    private lateinit var googleMap : GoogleMap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.mapView?.onCreate(savedInstanceState)
        binding?.mapView?.onResume()
        binding?.mapView?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        //Add marker for each store
        args.currentStores.forEach { store ->
            val latLng = LatLng(store.latitude!!,store.longitude!!)
            val markerOptions = MarkerOptions().position(latLng)
            googleMap.addMarker(markerOptions)
        }

        //Move the camera to the first store
        val firstStore = args.currentStores.firstOrNull()
        firstStore.let {
            val latLng = LatLng(firstStore?.latitude!!,firstStore.longitude!!)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
        }
    }
    override fun onPause() {
        super.onPause()
        binding?.mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding?.mapView?.onLowMemory()
    }
}
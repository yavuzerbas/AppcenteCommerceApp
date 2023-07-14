package com.example.appcentecommerceapp.data.model.reponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    val id: Long?,
    val productName: String?,
    val type: String?,
    val oldPrice: Double?,
    val newPrice: Double?,
    val productImage: String?,
    val quantity: Long?,
    val currentStore: String?,
) : Parcelable


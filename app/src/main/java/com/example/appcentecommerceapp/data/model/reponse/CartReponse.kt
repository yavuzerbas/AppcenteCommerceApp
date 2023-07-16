package com.example.appcentecommerceapp.data.model.reponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartResponse(
    val id: Long?,
    val token: String?,
    val products: List<ProductResponse>?,
) : Parcelable

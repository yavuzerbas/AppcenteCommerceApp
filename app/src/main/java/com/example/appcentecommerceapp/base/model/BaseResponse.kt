package com.example.appcentecommerceapp.base.model


data class BaseResponse<T>(
    val status: String?,
    val statusCode: Long?,
    val result: List<T>?,
    val message: String?,
) : java.io.Serializable
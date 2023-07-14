package com.example.appcentecommerceapp.base.model


data class BaseResponse<T>(
    val status: String?,
    val statusCode: Long?,
    val result: T?,
    val message: String?,
) : java.io.Serializable
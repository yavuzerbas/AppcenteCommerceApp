package com.example.appcentecommerceapp.base.notifier

interface CartStatusNotifier {
    fun updateCartStatus(isEmpty: Boolean)
}
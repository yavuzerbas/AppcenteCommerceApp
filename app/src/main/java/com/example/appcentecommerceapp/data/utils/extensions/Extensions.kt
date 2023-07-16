package com.example.appcentecommerceapp.data.utils.extensions

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get()
        .load(url)
        .into(this)
}

fun TextView.setPrice(price: String){
    this.text = "$$price"
}

fun TextView.setOldPrice(price : String){
    this.text = "$$price"
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
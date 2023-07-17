package com.example.appcentecommerceapp.ui.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ReturnHomeDialogFragment(private val listener: ReturnHomeListener, private val message: String) : DialogFragment() {

    interface ReturnHomeListener {
        fun onReturnHome()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Return Home")
                .setMessage(message)
                .setPositiveButton("Return to Home Page") { _, _ ->
                    listener.onReturnHome()
                }
                .setCancelable(false) // This prevents the dialog from being dismissed by back button or touching outside
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

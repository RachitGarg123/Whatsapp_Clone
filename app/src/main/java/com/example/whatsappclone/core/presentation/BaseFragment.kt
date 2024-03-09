package com.example.whatsappclone.core.presentation

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    fun showToast(context: Context, toastMessage: String, isDurationShort: Boolean) {
        if(isDurationShort) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun showSnackBar(view: View, snackBarMessage: String, isDurationShort: Boolean) {
        if(isDurationShort) {
            Snackbar.make(view, snackBarMessage, Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(view, snackBarMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}
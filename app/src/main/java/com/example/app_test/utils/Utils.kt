package com.example.app_test.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


class Utils{
    companion object{
        fun hideKeyboardy(context: Activity){
            val view = context.currentFocus
            if (view != null) {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}
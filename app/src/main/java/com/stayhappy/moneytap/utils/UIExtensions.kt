package com.stayhappy.moneytap.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun closeKeyBoard(context: Context, view: View?) {
    //close the keyboard
    if (view == null) return
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)

}
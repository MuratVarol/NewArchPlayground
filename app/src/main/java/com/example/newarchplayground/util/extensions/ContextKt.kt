package com.example.newarchplayground.util.extensions

import android.content.Context
import android.widget.Toast


fun Context.longToast(text: String?) {
    Toast.makeText(this, text.toString(), Toast.LENGTH_LONG).show()
}
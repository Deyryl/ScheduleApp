package com.vnmhpractice.scheduleapp.android.data.model

import android.net.Uri

data class User(
    val username: String,
    val email: String,
    val image: Uri? = null
)

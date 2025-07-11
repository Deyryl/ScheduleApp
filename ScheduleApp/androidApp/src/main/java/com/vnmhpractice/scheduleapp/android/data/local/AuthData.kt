package com.vnmhpractice.scheduleapp.android.data.local

import com.vnmhpractice.scheduleapp.android.data.datasource.user1
import com.vnmhpractice.scheduleapp.android.data.model.User

object AuthData {
    val user: User = user1
    private val savedToken: String = "abc"

    fun getSavedToken() : String = savedToken
}
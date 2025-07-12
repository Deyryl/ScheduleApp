package com.vnmhpractice.scheduleapp
import android.content.Context
import com.vnmhpractice.scheduleapp.data.dtoClasses.TokenPair
import com.vnmhpractice.scheduleapp.data.dtoClasses.TokenStorage

class SharedPrefsTokenStorage(
    context: Context
) : TokenStorage {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ACCESS  = "access_token"
        private const val KEY_REFRESH = "refresh_token"
    }

    override fun save(pair: TokenPair) {
        prefs.edit()
            .putString(KEY_ACCESS, pair.accessToken)
            .putString(KEY_REFRESH, pair.refreshToken)
            .apply()
    }

    override fun load(): TokenPair? {
        val access = prefs.getString(KEY_ACCESS, null)
        val refresh = prefs.getString(KEY_REFRESH, null)
        return if (access != null && refresh != null) {
            TokenPair(accessToken = access, refreshToken = refresh)
        } else {
            null
        }
    }
}
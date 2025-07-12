package com.vnmhpractice.scheduleapp
import android.content.Context
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenPair
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage

class SharedPrefsTokenStorage(
    context: Context
) : TokenStorage {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ACCESS  = "access_token"
        private const val KEY_REFRESH = "refresh_token"
    }

    override suspend fun save(pair: TokenPair) {
        prefs.edit()
            .putString(KEY_ACCESS, pair.accessToken)
            .putString(KEY_REFRESH, pair.refreshToken)
            .apply()
    }

    override suspend  fun load(): TokenPair? {
        val access = prefs.getString(KEY_ACCESS, null)
        val refresh = prefs.getString(KEY_REFRESH, null)
        return if (access != null && refresh != null) {
            TokenPair(accessToken = access, refreshToken = refresh)
        } else {
            null
        }
    }
}
package com.vnmhpractice.scheduleapp
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenPair
import platform.Foundation.NSUserDefaults


class TokenStorageImpl(): TokenStorage {
    private val prefs = NSUserDefaults.standardUserDefaults

    companion object {
        private const val ACCESS_KEY  = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }

    override suspend fun save(tokenPair: TokenPair) {
        prefs.setObject(tokenPair.accessToken, forKey = ACCESS_KEY)
        prefs.setObject(tokenPair.refreshToken, forKey = REFRESH_KEY)
        prefs.synchronize()
    }

    override suspend  fun load(): TokenPair? {
        val access = prefs.stringForKey(ACCESS_KEY)
        val refresh = prefs.stringForKey(REFRESH_KEY)
        return if (access != null && refresh != null) {
            TokenPair(accessToken = access, refreshToken = refresh)
        } else {
            null
        }
    }
}
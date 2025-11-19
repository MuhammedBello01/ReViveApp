package com.emperormoh.reviveapp.data.local

import com.emperormoh.reviveapp.common.AppConstants.REFRESH_TOKEN_KEY
import com.emperormoh.reviveapp.common.AppConstants.TOKEN_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenManager @Inject constructor(private val dataStore: AppDataStore){

    suspend fun saveToken(token: String) {
        dataStore.saveSecureData(TOKEN_KEY, token)
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.saveSecureData(REFRESH_TOKEN_KEY, refreshToken)
    }

    fun getToken(): Flow<String?> {
        return dataStore.getSecureData(TOKEN_KEY)
    }

    fun getRefreshToken(): Flow<String?> {
        return dataStore.getSecureData(REFRESH_TOKEN_KEY)
    }

    suspend fun deleteToken() {
        dataStore.clearPreference(TOKEN_KEY)
        dataStore.clearPreference(REFRESH_TOKEN_KEY)
    }
}
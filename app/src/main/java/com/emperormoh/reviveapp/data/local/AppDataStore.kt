package com.emperormoh.reviveapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.emperormoh.reviveapp.utils.EncryptionHelper
import com.emperormoh.reviveapp.utils.JsonUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Base64

class AppDataStore(context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "app_preferences")
    private val dataStore = context.dataStore

//    private val gson = GsonBuilder()
//        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer)
//        .registerTypeAdapter(BigDecimal::class.java, BigDecimalSerializer)
//        .create()

    suspend fun saveSecureData(key: String, value: String) {
        val (encryptedValue, iv) = EncryptionHelper.encrypt(value)
        val encryptedString = Base64.getEncoder().encodeToString(encryptedValue)
        val ivString = Base64.getEncoder().encodeToString(iv)

        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = "$encryptedString:$ivString"
        }
    }

    fun getSecureData(key: String): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]?.let { encryptedData ->
                val (data, iv) = encryptedData.split(":").map { Base64.getDecoder().decode(it) }
                EncryptionHelper.decrypt(data, iv)
            }
        }
    }

    suspend fun saveSecureComplexData(key: String, value: Any){
        val (encryptedValue, iv) = EncryptionHelper.encrypt(JsonUtils.toJson(value).orEmpty())
        val encryptedString = Base64.getEncoder().encodeToString(encryptedValue)
        val ivString = Base64.getEncoder().encodeToString(iv)

        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = "$encryptedString:$ivString"
        }
    }

    suspend fun <T> getSecureComplexData(key: String, type: Class<T>): T? {
        val storedData = dataStore.data.first()[stringPreferencesKey(key)] ?: return null

        val (encryptedString, ivString) = storedData.split(":")
        val encryptedBytes = Base64.getDecoder().decode(encryptedString)
        val ivBytes = Base64.getDecoder().decode(ivString)

        return try{
            val decryptedJson = EncryptionHelper.decrypt(encryptedBytes, ivBytes)  // 🔓 Decrypt JSON
            return JsonUtils.gson.fromJson(decryptedJson, type)  // 📜 Convert back to object
        }catch (e: Exception) {
            null  // Return null if decryption or deserialization fails
        }
    }

    suspend fun clearPreference(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }

    suspend fun clearAll() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
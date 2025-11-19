package com.emperormoh.reviveapp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.math.BigDecimal
import java.time.LocalDateTime

object JsonUtils {

     val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer)
        .registerTypeAdapter(BigDecimal::class.java, BigDecimalSerializer)
        .create()

    fun toJson(data: Any): String? {
        return try {
            gson.toJson(data)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
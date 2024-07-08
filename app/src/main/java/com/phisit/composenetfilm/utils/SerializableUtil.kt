package com.phisit.composenetfilm.utils

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? {
       return bundle.getString(key)?.let{
           URLDecoder.decode(it, StandardCharsets.UTF_8.toString()).let { decode ->
               json.decodeFromString(decode)
           }
       }
    }

    override fun parseValue(value: String): T {
        return URLDecoder.decode(value, StandardCharsets.UTF_8.toString()).let { decode ->
            json.decodeFromString(decode)
        }
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        val jsonStr = json.encodeToString(value)
        URLEncoder.encode(jsonStr, StandardCharsets.UTF_8.toString()).let { encoded ->
            bundle.putString(key, encoded)
        }
    }

    override fun serializeAsValue(value: T): String {
        val jsonStr = json.encodeToString(value)
        return URLEncoder.encode(jsonStr, StandardCharsets.UTF_8.toString())
    }

}
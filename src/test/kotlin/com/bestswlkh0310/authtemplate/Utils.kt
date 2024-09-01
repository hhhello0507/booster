package com.bestswlkh0310.authtemplate

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue


fun Any.toJson(): String = try {
    jacksonObjectMapper().writeValueAsString(this)
} catch (e: Exception) {
    throw RuntimeException(e)
}

inline fun <reified T> String.fromJson(): T = try {
    jacksonObjectMapper().readValue<T>(this)
} catch (e: Exception) {
    throw RuntimeException(e)
}
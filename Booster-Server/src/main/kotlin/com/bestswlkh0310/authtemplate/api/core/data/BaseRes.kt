package com.bestswlkh0310.authtemplate.api.core.data

import org.springframework.http.HttpStatus

data class BaseRes<Data>(
    val status: Int,
    val message: String,
    val data: Data
) {
    companion object {
        fun <Data> ok(data: Data) = BaseRes(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            data
        )
    }
}
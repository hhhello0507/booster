package com.bestswlkh0310.booster.api.core.data.res

import org.springframework.http.HttpStatus

data class BaseRes<Data>(
    val status: Int,
    val message: String,
    val data: Data
) {
    constructor(
        httpStatus: HttpStatus,
        data: Data
    ): this(status = httpStatus.value(), message = httpStatus.reasonPhrase, data = data)
    
    companion object {
        fun <Data> ok(data: Data) = BaseRes(
            httpStatus = HttpStatus.OK,
            data = data
        )
        
        fun <Data> created(data: Data) = BaseRes(
            httpStatus = HttpStatus.CREATED,
            data = data
        )
    }
}
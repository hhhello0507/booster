package com.bestswlkh0310.booster.api.core.data.res

import org.springframework.http.HttpStatus

data class BaseRes<DATA>(
    val status: Int,
    val message: String,
    val data: DATA
) {
    constructor(
        httpStatus: HttpStatus,
        data: DATA
    ): this(status = httpStatus.value(), message = httpStatus.reasonPhrase, data = data)
    
    companion object {
        fun <DATA> ok(data: DATA) = BaseRes(
            httpStatus = HttpStatus.OK,
            data = data
        )
        
        fun <DATA> created(data: DATA) = BaseRes(
            httpStatus = HttpStatus.CREATED,
            data = data
        )
    }
}
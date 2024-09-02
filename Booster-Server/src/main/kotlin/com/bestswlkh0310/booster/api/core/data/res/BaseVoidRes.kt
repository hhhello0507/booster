package com.bestswlkh0310.booster.api.core.data.res

import org.springframework.http.HttpStatus

data class BaseVoidRes(
    val status: Int,
    val message: String
) {
    
    companion object {
        fun ok() = BaseVoidRes(
            status = HttpStatus.OK.value(),
            message = HttpStatus.OK.reasonPhrase
        )
        
        fun created() = BaseVoidRes(
            status = HttpStatus.CREATED.value(),
            message = HttpStatus.CREATED.reasonPhrase
        )
    }
}
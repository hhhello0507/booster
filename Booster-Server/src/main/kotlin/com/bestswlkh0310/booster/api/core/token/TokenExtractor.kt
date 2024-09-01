package com.bestswlkh0310.booster.api.core.token

import com.bestswlkh0310.booster.global.exception.CustomException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

object TokenExtractor {
    
    const val AUTHORIZATION = "Authorization"
    
    fun extract(request: HttpServletRequest): String? {
        val authorization = request.getHeader(AUTHORIZATION) ?: return null
        return token(authorization)
    }

    private fun token(authorization: String): String {
        if (!authorization.startsWith("Bearer ")) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "token does not start with Bearer")
        }
        return authorization.removePrefix("Bearer ")
    }
}
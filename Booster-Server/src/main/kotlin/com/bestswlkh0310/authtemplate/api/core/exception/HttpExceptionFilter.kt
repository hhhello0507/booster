package com.bestswlkh0310.authtemplate.api.core.exception

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Component
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.filter.OncePerRequestFilter

@Component
class HttpExceptionFilter(
    private val sender: ErrorResponseSender
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: HttpRequestMethodNotSupportedException) {
            sender.send(response = response, status = HttpStatus.METHOD_NOT_ALLOWED)
        } catch (e: HttpMessageNotReadableException) {
            sender.send(response = response, status = HttpStatus.BAD_REQUEST)
        }
    }
}
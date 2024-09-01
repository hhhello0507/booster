package com.bestswlkh0310.authtemplate.api.core.exception

import com.bestswlkh0310.authtemplate.api.core.data.BaseVoidRes
import com.bestswlkh0310.authtemplate.global.exception.CustomException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class ErrorResponseSender(
    private val objectMapper: ObjectMapper
) {

    fun send(response: HttpServletResponse, customException: CustomException) =
        send(response, customException.status, customException.message)

    fun send(response: HttpServletResponse, status: HttpStatus, message: String? = null) {
        try {
            response.apply {
                this.status = status.value()
                contentType = MediaType.APPLICATION_JSON_VALUE
                characterEncoding = "UTF-8"
            }
            response.writer.write(
                objectMapper.writeValueAsString(
                    BaseVoidRes(
                        status = status.value(),
                        message = message ?: status.reasonPhrase
                    )
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
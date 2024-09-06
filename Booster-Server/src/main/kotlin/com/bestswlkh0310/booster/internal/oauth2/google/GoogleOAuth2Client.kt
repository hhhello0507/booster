package com.bestswlkh0310.booster.internal.oauth2.google

import com.bestswlkh0310.booster.global.exception.CustomException
import com.bestswlkh0310.booster.internal.oauth2.google.data.res.GoogleOAuth2TokenRes
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

@Component
class GoogleOAuth2Client(
    private val properties: GoogleOAuth2Properties,
    @Qualifier("google")
    private val restClient: RestClient
) {

    fun getToken(code: String) = restClient.post()
        .uri {
            it.path("token")
                .queryParam("client_id", properties.webClientId)
                .queryParam("client_secret", properties.clientSecret)
                .queryParam("code", code)
                .queryParam("grant_type", properties.grantType)
                .queryParam("redirect_uri", properties.redirectUri)
                .build()
        }
        .retrieve()
        .toEntity<GoogleOAuth2TokenRes>()
        .body ?: throw CustomException(HttpStatus.UNAUTHORIZED, "invalid google oauth2 code")
}

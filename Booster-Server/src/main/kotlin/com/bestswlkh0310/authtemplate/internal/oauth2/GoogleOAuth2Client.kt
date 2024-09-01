package com.bestswlkh0310.authtemplate.internal.oauth2

import com.bestswlkh0310.authtemplate.global.exception.CustomException
import com.bestswlkh0310.authtemplate.internal.core.OAuth2Properties
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2Client(
    private val oAuth2Properties: OAuth2Properties,
//    @GoogleOAuth2RestClient
//    private val restClient: RestClient
) {

    // for web
//    fun getToken(code: String): GoogleOAuth2TokenRes {
//        val response = restClient.post()
//            .uri {
//                it.path("token")
//                    .queryParam("client_id", oAuth2Properties.googleClientId)
//                    .queryParam("client_secret", oAuth2Properties.googleClientSecret)
//                    .queryParam("code", URLDecoder.decode(code, StandardCharsets.UTF_8))
//                    .queryParam("grant_type", "authorization_code")
//                    .queryParam("redirect_uri", oAuth2Properties.googleRedirectUri)
//                    .build()
//            }
//            .retrieve()
//            .toEntity(GoogleOAuth2TokenRes::class.java)
//        return response.body ?: throw CustomException(HttpStatus.UNAUTHORIZED, "invalid google oauth2 code")
//    }
//
//    fun getResource(idToken: String): GoogleOAuth2ResourceRes {
//        val response = restClient.post()
//            .uri {
//                it.path("tokeninfo")
//                    .queryParam("id_token", idToken)
//                    .build()
//            }
//            .retrieve()
//            .toEntity(GoogleOAuth2ResourceRes::class.java)
//        return response.body ?: throw CustomException(HttpStatus.UNAUTHORIZED, "invalid google oauth2 token")
//    }

    fun verifyIdToken(idToken: String): GoogleIdToken {
        println(idToken)
        val verifier = GoogleIdTokenVerifier.Builder(
            NetHttpTransport(),
            GsonFactory()
        )
            .setAudience(
                listOf(
                    oAuth2Properties.googleClientIdIOS,
                    oAuth2Properties.googleClientIdWeb
                )
            )
            .build()

        return verifier.verify(idToken)
            ?: throw CustomException(HttpStatus.UNAUTHORIZED, "Invalid id token")
    }
}
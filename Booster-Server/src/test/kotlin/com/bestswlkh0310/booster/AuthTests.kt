package com.bestswlkh0310.booster

import com.bestswlkh0310.booster.api.auth.data.req.*
import com.bestswlkh0310.booster.api.auth.data.res.TokenRes
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

//@TestAnnotation
class BoosterApplicationTests {

    init {
        Environment.initEnvironment()
    }

    @Autowired
    lateinit var mvc: MockMvc

    // 유저가 없는 경우
    @Test
    fun signIn1() {
        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignInReq(username = "12", password = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().`is`(404))
            .andReturn().response.contentAsString
            .let(::println)
    }

    // password, passwordCheck가 다른 경우
    @Test
    fun signUp1() {
        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignUpReq(username = "12", password = "12", passwordCheck = "123", nickname = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().`is`(400))
            .andReturn().response.contentAsString
            .let(::println)
    }

    @Test
    fun `signUp and signIn`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignUpReq(username = "12", password = "12", passwordCheck = "12", nickname = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let(::println)

        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignInReq(username = "12", password = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let(::println)
    }

    @Test
    fun `signUp, signIn, test`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignUpReq(username = "12", password = "12", passwordCheck = "12", nickname = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let(::println)

        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignInReq(username = "12", password = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let {
                val response: BaseRes<TokenRes> = it.fromJson()
                mvc.perform(
                    MockMvcRequestBuilders.get("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer ${response.data.accessToken}")
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn().response.contentAsString
                    .let(::println)
            }
    }


    @Test
    fun `signUp, signIn, refresh`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignUpReq(username = "12", password = "12", passwordCheck = "12", nickname = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let(::println)

        mvc.perform(
            MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    SignInReq(username = "12", password = "12").toJson()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
            .let {
                val response: BaseRes<TokenRes> = it.fromJson()
                mvc.perform(
                    MockMvcRequestBuilders.post("/auth/refresh")
                        .content(
                            RefreshReq(
                                refreshToken = response.data.refreshToken,
                            ).toJson()
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer ${response.data.accessToken}")
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn().response.contentAsString
                    .let(::println)
            }
    }
//    
//    @Test
//    fun oauth2SignIn() {
//        mvc.perform(
//            MockMvcRequestBuilders.post("/auth/sign-in/oauth2")
//                .content(
//                    OAuth2SignInReq(
//                        platformType = PlatformType.GOOGLE,
//                        idToken = "wowㅋㅋ"
//                    ).toJson()
//                )
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//            .andReturn().response.contentAsString
//            .let(::println)
//    }
}

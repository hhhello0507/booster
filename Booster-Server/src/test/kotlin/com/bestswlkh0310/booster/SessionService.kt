package com.bestswlkh0310.booster

import com.bestswlkh0310.booster.api.auth.data.req.SignInReq
import com.bestswlkh0310.booster.api.auth.data.req.SignUpReq
import com.bestswlkh0310.booster.api.auth.data.res.TokenRes
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

fun MockMvc.session(): TokenRes {
    this.perform(
        MockMvcRequestBuilders.post("/auth/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                SignUpReq(username = "12", password = "12", passwordCheck = "12", nickname = "12").toJson()
            )
    ).andExpect(MockMvcResultMatchers.status().isOk)
        .andReturn().response.contentAsString
        .let(::println)

    val response = this.perform(
        MockMvcRequestBuilders.post("/auth/sign-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                SignInReq(username = "12", password = "12").toJson()
            )
    ).andExpect(MockMvcResultMatchers.status().isOk)
        .andReturn().response.contentAsString
    val data: BaseRes<TokenRes> = response.fromJson()
    return data.data
}
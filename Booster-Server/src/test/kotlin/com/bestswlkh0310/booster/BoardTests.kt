package com.bestswlkh0310.booster

import com.bestswlkh0310.booster.api.auth.data.res.TokenRes
import com.bestswlkh0310.booster.api.board.data.req.CreateBoardReq
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestAnnotation
class BoardTests {

    init {
        Environment.initEnvironment()
    }

    @Autowired
    lateinit var mvc: MockMvc
    lateinit var token: TokenRes
    
    @BeforeEach
    fun initToken() {
        token = mvc.session()
    }
    
    @Test
    fun create() {
        mvc.perform(
            MockMvcRequestBuilders.post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    CreateBoardReq(
                        content = "test"
                    ).toJson()
                )
                .header("Authorization", "Bearer ${token.accessToken}")
        ).andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(::println)
    }

    @Test
    fun get() {
        create()
        create()
        create()
        create()
        mvc.perform(
            MockMvcRequestBuilders.get("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer ${token.accessToken}")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(::println)
    }
}
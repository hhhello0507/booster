package com.bestswlkh0310.authtemplate

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestAnnotation
class TestTests {
    
    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun test() {
        mvc.perform(
            MockMvcRequestBuilders.get("/test")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized)
            .andReturn().response.contentAsString
            .let(::println)
    }
}
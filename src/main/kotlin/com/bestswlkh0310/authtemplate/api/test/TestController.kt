package com.bestswlkh0310.authtemplate.api.test

import com.bestswlkh0310.authtemplate.api.core.data.BaseRes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    @GetMapping("", "/")
    fun test() = ResponseEntity.ok(
        BaseRes.ok("it's test. wow 🤩")
    )
}
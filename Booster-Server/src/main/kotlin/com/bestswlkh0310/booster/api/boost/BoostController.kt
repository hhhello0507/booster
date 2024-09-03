package com.bestswlkh0310.booster.api.boost

import com.bestswlkh0310.booster.api.boost.data.req.CreateBoostReq
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boost")
class BoostController(
    private val boostService: BoostService
) {
    @PostMapping
    fun createBoost(
        @RequestBody @Valid req: CreateBoostReq
    ) = boostService.createBoost(req)
}
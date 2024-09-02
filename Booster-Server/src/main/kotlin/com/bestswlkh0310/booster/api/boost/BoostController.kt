package com.bestswlkh0310.booster.api.boost

import org.springframework.web.bind.annotation.PostMapping
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
        @RequestParam("boardId") boardId: Long
    ) = boostService.createBoost(boardId)
}
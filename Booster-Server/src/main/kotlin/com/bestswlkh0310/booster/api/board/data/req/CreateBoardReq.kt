package com.bestswlkh0310.booster.api.board.data.req

import jakarta.validation.constraints.NotBlank

data class CreateBoardReq(
    @field:NotBlank
    val content: String
)
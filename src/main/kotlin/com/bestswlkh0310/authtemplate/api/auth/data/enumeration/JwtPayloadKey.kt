package com.bestswlkh0310.authtemplate.api.auth.data.enumeration

enum class JwtPayloadKey(
    val key: String
) {
    ID("id"),
    USERNAME("username"),
    ROLE("role");
}
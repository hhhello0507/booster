package com.bestswlkh0310.authtemplate.api.auth

import com.bestswlkh0310.authtemplate.api.auth.data.req.OAuth2SignInReq
import com.bestswlkh0310.authtemplate.api.auth.data.req.RefreshReq
import com.bestswlkh0310.authtemplate.api.auth.data.req.SignInReq
import com.bestswlkh0310.authtemplate.api.auth.data.req.SignUpReq
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid req: SignUpReq) =
        ResponseEntity.ok(authService.signUp(req))

    @PostMapping("/sign-in")
    fun signIn(@RequestBody @Valid req: SignInReq) =
        ResponseEntity.ok(authService.signIn(req))

    @PostMapping("/refresh")
    fun refresh(@RequestBody @Valid req: RefreshReq) =
        ResponseEntity.ok(authService.refresh(req))

    @PostMapping("/sign-in/oauth2")
    fun oAuth2SignIn(@RequestBody @Valid req: OAuth2SignInReq) =
        ResponseEntity.ok(authService.oAuth2SignIn(req))
}
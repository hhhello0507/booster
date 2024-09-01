package com.bestswlkh0310.authtemplate.api.auth.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class BCryptConfig {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
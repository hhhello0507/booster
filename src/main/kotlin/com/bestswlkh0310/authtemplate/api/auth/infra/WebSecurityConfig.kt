package com.bestswlkh0310.authtemplate.api.auth.infra

import com.bestswlkh0310.authtemplate.api.core.exception.HttpExceptionFilter
import com.bestswlkh0310.authtemplate.api.core.exception.ErrorResponseSender
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtExceptionFilter: JwtExceptionFilter,
    private val httpExceptionFilter: HttpExceptionFilter,
    private val sender: ErrorResponseSender
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain = http
        .cors { corsConfigurationSource() }
        .csrf { it.disable() }
        .formLogin { it.disable() }
        .sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .authorizeHttpRequests {
            it.requestMatchers(
                "/auth/sign-up",
                "/auth/sign-in",
                "/auth/sign-in/oauth2",
                "/auth/refresh"
            ).permitAll()
                .anyRequest().authenticated()
        }
        .exceptionHandling {
            it.authenticationEntryPoint { _, response, _ -> sender.send(response, HttpStatus.UNAUTHORIZED) }
            it.accessDeniedHandler { _, response, _ -> sender.send(response, HttpStatus.FORBIDDEN) }
        }
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter::class.java)
        .addFilterBefore(httpExceptionFilter, JwtExceptionFilter::class.java)
        .build()
    
    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedOriginPattern(CorsConfiguration.ALL)  // Allows any origin
            addAllowedHeader(CorsConfiguration.ALL)         // Allows any header
            addAllowedMethod(CorsConfiguration.ALL)         // Allows any HTTP method
            allowCredentials = true                         // Allows cookies and credentials
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }
}
package com.bestswlkh0310.authtemplate.api.auth.infra


import com.bestswlkh0310.authtemplate.foundation.user.data.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUserDetails(
    private val user: User
) : UserDetails {
    override fun getAuthorities() = listOf(GrantedAuthority { user.role.name })
    override fun getPassword() = user.password
    override fun getUsername() = user.username
    override fun isAccountNonExpired() = true // 계정이 만료 되었는지 (true: 만료X)
    override fun isAccountNonLocked() = true // 계정이 잠겼는지 (true: 잠기지 않음)
    override fun isCredentialsNonExpired() = true // 비밀번호가 만료되었는지 (true: 만료X)
    override fun isEnabled() = true
}
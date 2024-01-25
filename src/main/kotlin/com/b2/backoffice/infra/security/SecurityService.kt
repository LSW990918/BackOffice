package com.b2.backoffice.infra.security

import com.b2.backoffice.domain.exception.InvalidCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityService(
    private val passwordEncoder: PasswordEncoder,

) {
    fun chkUserId(userPrincipal: UserPrincipal, userId:Int){
        // MANAGER 나 USER의 경우 본인의 토큰 id 와 조회할 대상의 userId 를 검사함
        if (userPrincipal.authoricies.toString() != "[ROLE_ADMIN]" && userPrincipal.id != userId) {
            throw IllegalArgumentException("Invalid role (${userPrincipal.authoricies.toString()})")
        }
    }

    fun chkPassword(rawPw: String, encodePw: String) {
        if (!passwordEncoder.matches(rawPw, encodePw))
            throw InvalidCredentialsException("Invalid password")
    }
}
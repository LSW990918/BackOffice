package com.b2.backoffice.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer : String,
    @Value("\${auth.jwt.secret}") private val secret : String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour : Long,
) {
//        companion object {
//        const val SECRET = "gH7kRw9FpD3yN2mQxL5aJcUvZ1oP4bS6" // 아무키 32자 테스트
//        const val ISSUER = "team.sparta.com"
//        const val ACCESS_TOKEN_EXPIRATION_HOUR : Long = 168
//    }

    //검증 메서드
    fun validateToken(jwt : String) : Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8) )

            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt) // 검증해주기
        }
    }

    //액세스 토큰 생성
    fun generateAccessToken(subject: String, email: String, role : String) : String{
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    fun generateToken(subject: String, email: String, role: String, expirationPeriod: Duration) : String{
        val claims : Claims = Jwts.claims()
            .add(mapOf("role" to role, "email" to email))
            .build()

        val now = Instant.now()

        val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }

}
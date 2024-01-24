package com.b2.backoffice.infra.security.jwt

import com.b2.backoffice.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin  // 토큰 검증
) : OncePerRequestFilter() { // 매 요청 마다 JWT 확인, 검증. Spring Web 제공

    companion object{
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")

    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if(jwt != null)
        {
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    val userId = it.payload.subject.toInt()
                    val role = it.payload.get("role", String::class.java)
                    val email = it.payload.get("email", String::class.java)

                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        role = setOf(role)
                    )

                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                }
        }
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken() : String?{ // Bearer토큰이 없을 수도 있음
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) //그냥 Headers 에 정의된 String 상수
            ?: return null // 위 키워드를 기반으로 헤더값을 가져올 수 있음 // Bearer {JWT} 로 되어있음

        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
        // 헤더값들을 getHeader로 가져왔는데 그중 PATTERN에 맞는 것 중에 첫번 째 것만 가져오기
    }
}
package com.b2.backoffice.infra.security

import com.b2.backoffice.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig (
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
){



    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()}  // 사용하지 않는 필터 끄기
            .formLogin{it.disable()}
            .csrf{ it.disable()} // 일종의 보안 공격 방지. 꺼버림.\
            .headers { it.frameOptions{ option -> option.sameOrigin() } } // H2 설정
            // 요청에 대한 인가 규칙 정의
            .authorizeHttpRequests{
                it.requestMatchers( // 특정 경로 설정 지정
                    "/users/logIn",
                    "/users/signUp",
                    "/swagger-ui/**", // swagger페이지
                    "v3/api-docs/**", // 내용 docs
                    "/h2-console/**",
                    "/error"       // security 에서 내부적으로 에러 발생시 이 경로로 리다이렉션 됨. -> 새 요청에 인가가 안되서 먼저 에러가 jwt invalid
                ).permitAll() // 위 URL은 승인처리
                    .anyRequest() // 나머지 요청들은
                    .authenticated() // 인증이 된 사용자만 허용
            }
            // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 추가
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .logout { // 로그아웃 설정
                it.logoutUrl("/users/logout") // 로그아웃 URL 지정
                    .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트될 URL 지정
                    .invalidateHttpSession(true) // HTTP 세션 무효화 여부
                    .deleteCookies("JSESSIONID", "JWT_TOKEN") // 삭제할 쿠키 목록
            }
            .exceptionHandling{ // 예외 처리 관련 설정
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler) // 에러 연결하기
            }
            .build()
    }
}
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
    fun filterChain(http : HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic{it.disable()}
            .formLogin{it.disable()}
            .csrf{it.disable()}
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/logIn",
                    "/signUp",
                    "/swagger-ui/**",
                    "v3/api-docs/**",
                ).permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler) // 에러 연결하기
            }
            .build()
    }
}
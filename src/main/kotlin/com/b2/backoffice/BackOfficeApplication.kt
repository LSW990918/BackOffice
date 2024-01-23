package com.b2.backoffice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class]) // Security 자동 login 설정 방지 //  Spring Security에 관련된 자동 구성을 비활성화
class BackOfficeApplication

fun main(args: Array<String>) {
    runApplication<BackOfficeApplication>(*args)
}

package com.b2.backoffice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackOfficeApplication

fun main(args: Array<String>) {
    runApplication<BackOfficeApplication>(*args)
}

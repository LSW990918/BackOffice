package com.b2.backoffice.domain.exception

import com.b2.backoffice.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message))
    }

}
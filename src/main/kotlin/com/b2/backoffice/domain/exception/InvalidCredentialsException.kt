package com.b2.backoffice.domain.exception

data class InvalidCredentialsException(val target:String) : RuntimeException(
    "The credential is invalid. $target does not match."
)

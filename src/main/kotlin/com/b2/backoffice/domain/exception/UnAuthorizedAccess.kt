package com.b2.backoffice.domain.exception


class UnAuthorizedAccess(
    override val message: String
): RuntimeException(message) {
}
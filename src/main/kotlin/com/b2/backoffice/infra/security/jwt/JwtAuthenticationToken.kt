package com.b2.backoffice.infra.security.jwt

import com.b2.backoffice.infra.security.UserPrincipal
import org.hibernate.metamodel.mapping.TableDetails.KeyDetails
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
    private val principal: UserPrincipal,
    details: WebAuthenticationDetails
): AbstractAuthenticationToken(principal.authoricies)
{
    init{
        super.setAuthenticated(true)
        super.setDetails(details)
    }
    override fun getPrincipal() = principal
    override fun getCredentials() = null

    override fun isAuthenticated(): Boolean {
        return true
    }

}
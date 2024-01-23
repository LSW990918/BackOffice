package com.b2.backoffice.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id : Long,
    val email : String,
    val authoricies : Collection<GrantedAuthority>
){
    constructor(id:Long, email:String, role:Set<String>):this(
        id,
        email,
        role.map{ SimpleGrantedAuthority("ROLE_$it") }
    )
}

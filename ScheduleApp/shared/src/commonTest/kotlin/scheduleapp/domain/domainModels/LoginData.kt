package com.vnmhpractice.scheduleapp.domain.domainModels

import com.vnmhpractice.scheduleapp.data.dtoClasses.TokensDTO

data class LoginData (
    val tokenPair: TokensDTO,
    val user: User
)
package com.vnmhpractice.scheduleapp.data.dtoClasses

data class AuthResponse(
    val tokensDTO: TokensDTO,
    val username: String,
    val email: String,
    val imageURL: String?,
    val projectIds: List<String>?
)
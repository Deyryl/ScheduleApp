package com.vnmhpractice.scheduleapp.domain.domainModels

data class User (
    val username: String,
    val email: String,
    val password: String,
    val imageURL: String?,
    val projectIds: List<String>?
)
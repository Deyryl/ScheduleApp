package com.vnmhpractice.scheduleapp.data.mapper

import com.vnmhpractice.scheduleapp.data.dtoClasses.*
import com.vnmhpractice.scheduleapp.domain.domainModels.*

// UserMappers

fun UserDTO.toDomain(password: String = ""): User = User(
    username = username,
    email = email,
    imageURL = imageURL,
    projectIds = projectIds
)

fun User.toDTO(): UserDTO = UserDTO(
    username = username,
    email = email,
    imageURL = imageURL,
    projectIds = projectIds
)

//AuthMappers

fun User.toRequest(password: String): AuthRequest = AuthRequest(
    username = username,
    email = email,
    password = password
)

fun AuthResponse.toDomain(): LoginData = LoginData (
    tokenPair = tokensDTO,
    user = User (
        username = username,
        email = email,
        imageURL = imageURL,
        projectIds = projectIds
    )
)


// ProjectMappers

fun ProjectDTO.toDomain(): Project = Project(
    projectId = projectId,
    title = title,
    ownerId = ownerId,
    moderatorIds = moderatorIds ?: emptyList(),
    memberIds = memberIds ?: emptyList(),
    imageURL = imageURL
    // taskIds и tagIds могут быть добавлены позже
)

fun Project.toDTO(): ProjectDTO = ProjectDTO(
    projectId = projectId,
    title = title,
    ownerId = ownerId,
    moderatorIds = moderatorIds,
    memberIds = memberIds,
    imageURL = imageURL
    // tasks и tags пока опущены
)

// Tokens

fun TokensDTO.toDomain(): TokenPair = TokenPair(
    accessToken = accessToken,
    refreshToken = refreshToken
)
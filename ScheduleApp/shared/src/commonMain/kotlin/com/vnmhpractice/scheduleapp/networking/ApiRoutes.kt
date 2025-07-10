package com.vnmhpractice.scheduleapp.networking

object ApiRoutes {
    const val BASE_URL = "https://localhost:8085"

    object Auth {
        const val LOGIN = "$BASE_URL/auth/login"
        const val REGISTER = "$BASE_URL/auth/register"
        const val REFRESH = "$BASE_URL/auth/refresh"
    }

    object Projects {
        const val ALL = "$BASE_URL/projects"
        val BY_ID = { id: String -> "$BASE_URL/projects/$id" }
    }

    object Users {
        const val PROFILE = "$BASE_URL/users/profile"
    }
}
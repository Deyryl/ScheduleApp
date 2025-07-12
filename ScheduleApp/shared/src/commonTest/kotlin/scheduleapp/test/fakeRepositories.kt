// shared/src/commonTest/kotlin/com/vnmhpractice/scheduleapp/domain/FakeUserRepository.kt
package scheduleapp.domain.test

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthResponse
import com.vnmhpractice.scheduleapp.data.dtoClasses.TokensDTO
import com.vnmhpractice.scheduleapp.domain.domainModels.User
import com.vnmhpractice.scheduleapp.domain.repositories.UserRepository
import util.NetworkError
import util.Result

// Всегда успешный репозиторий
class SuccessUserRepository : UserRepository {
    override suspend fun registerUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        val dto = AuthResponse(
            tokensDTO = TokensDTO("access", "refresh"),
            username = authRequest.username ?: "default",
            email = authRequest.email,
            imageURL = null,
            projectIds = emptyList()
        )
        return Result.Success(dto)
    }

    override suspend fun loginUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        // просто делегируем register для теста
        return registerUser(authRequest)
    }
}

// Всегда «неуспешный» репозиторий
class ErrorUserRepository : UserRepository {
    override suspend fun registerUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        return Result.Error(NetworkError.UNKNOWN)
    }
    override suspend fun loginUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        return Result.Error(NetworkError.UNAUTHORIZED)
    }
}

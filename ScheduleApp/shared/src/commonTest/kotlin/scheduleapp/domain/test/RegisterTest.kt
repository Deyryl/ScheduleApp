// shared/src/commonTest/kotlin/com/vnmhpractice/scheduleapp/domain/RegisterUserTest.kt
package scheduleapp.domain.test

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.domain.domainModels.*
import com.vnmhpractice.scheduleapp.domain.useCases.RegisterUser
import kotlinx.coroutines.test.runTest
import kotlin.test.*
import scheduleapp.domain.test.SuccessUserRepository
import scheduleapp.domain.test.ErrorUserRepository

class RegisterUserTest {

    @Test
    fun `successful registration returns LoginData`() = runTest {
        val repo = SuccessUserRepository()
        val uc = RegisterUser(repo)

        val result = uc("john", "john@mail.com", "pass", "pass")

        assertNotNull(result, "Должен вернуть LoginData для совпадающих паролей")
        assertEquals("john", result?.user?.username)
        assertEquals("john@mail.com", result?.user?.email)
    }

    @Test
    fun `registration with non-matching passwords throws exception`() = runTest {
        val repo = SuccessUserRepository()
        val uc = RegisterUser(repo)

        val err = assertFailsWith<IllegalArgumentException> {
            uc("john", "john@mail.com", "pass", "wrong")
        }
        assertEquals("Ошибка регистрации", err.message)
    }

    @Test
    fun `repository error returns null`() = runTest {
        val repo = ErrorUserRepository()
        val uc = RegisterUser(repo)

        val result = uc("john", "john@mail.com", "pass", "pass")
        assertNull(result, "При ошибке из репозитория должен вернуться null")
    }
}

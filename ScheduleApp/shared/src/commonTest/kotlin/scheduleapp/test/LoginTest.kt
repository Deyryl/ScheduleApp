
package scheduleapp.test

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.domain.domainModels.*
import com.vnmhpractice.scheduleapp.domain.useCases.LoginUser
import com.vnmhpractice.scheduleapp.domain.useCases.RegisterUser
import kotlinx.coroutines.test.runTest
import kotlin.test.*
import scheduleapp.domain.test.SuccessUserRepository
import scheduleapp.domain.test.ErrorUserRepository

class LoginUserTest {

    @Test
    fun `successful login returns LoginData`() = runTest {
        val repo = SuccessUserRepository()
        val uc = LoginUser(repo)

        val result = uc("john@mail.com", "pass")

        assertNotNull(result, "Должен вернуть LoginData при успешном логине")
        assertEquals("default", result?.user?.username)  // SuccessUserRepository возвращает username="default"
        assertEquals("john@mail.com", result?.user?.email)
    }

    @Test
    fun `repository unauthorized error throws IllegalArgumentException`() = runTest {
        val repo = ErrorUserRepository()
        val uc = LoginUser(repo)

        val err = assertFailsWith<IllegalArgumentException> {
            uc("john@mail.com", "pass")
        }
        assertEquals("Неверные данные", err.message)
    }
}

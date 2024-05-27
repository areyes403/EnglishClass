package mx.edu.itm.link.englishclass

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.SignIn
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SignInInstrumentedTest {

    // Mock repositorios
    @Mock lateinit var authRepo: AuthRepository
    @Mock lateinit var userRepo: UserRepository
    @Mock lateinit var roomRepo: RoomRepository

    // Objeto a probar
    private lateinit var signIn: SignIn

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        signIn = SignIn(authRepo, userRepo, roomRepo)
    }

    @Test
    suspend fun signIn_success() {
        // Obtener el contexto de la aplicación
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Mock datos de entrada
        val email = "test@example.com"
        val password = "password123"

        // Mock resultado de la autenticación
        val authResult = ResponseStatus.Success("userId")
        `when`(authRepo.login(email, password)).thenReturn(authResult)

        // Mock resultado de obtener usuario
        val user = User("userId", "Test User")
        val userResult = ResponseStatus.Success(user)
        `when`(userRepo.getUser("userId")).thenReturn(userResult)

        // Ejecutar el caso de uso dentro del contexto de la aplicación
        runBlocking {
            val result = signIn.invoke(email, password)
            // Verificar resultado
            assertEquals(ResponseStatus.Success(Unit), result)
        }

        // Verificar que se haya insertado el usuario en la base de datos local
        verify(roomRepo).insertUser(user)
    }

    @Test
    suspend fun signIn_auth_error() {
        // Obtener el contexto de la aplicación
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Mock datos de entrada
        val email = "test@example.com"
        val password = "password123"

        // Mock resultado de la autenticación (error)
        val authResult = ResponseStatus.Error("Auth error")
        `when`(authRepo.login(email, password)).thenReturn(authResult)

        // Ejecutar el caso de uso dentro del contexto de la aplicación
        runBlocking {
            val result = signIn.invoke(email, password)
            // Verificar resultado
            assertEquals(authResult, result)
        }
    }

    // Agregar más pruebas según sea necesario para otros casos de uso
}
package mx.edu.itm.link.englishclass.domain.usecase.authusecase

data class AuthUseCases(
    val signIn: SignIn,
    val signUp: SignUp,
    val getCurrentUser: GetCurrentUser
)
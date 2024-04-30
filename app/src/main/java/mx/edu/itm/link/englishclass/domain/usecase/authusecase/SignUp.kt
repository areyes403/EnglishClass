package mx.edu.itm.link.englishclass.domain.usecase.authusecase


import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.domain.repository.AuthRepository

class SignUp (
    private val repo: AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String,
        user: User
    ) = repo.register(email = email, password = password, user = user)
}
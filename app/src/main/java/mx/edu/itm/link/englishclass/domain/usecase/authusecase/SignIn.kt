package mx.edu.itm.link.englishclass.domain.usecase.authusecase

import mx.edu.itm.link.englishclass.domain.repository.AuthRepository

class SignIn(
    private val repo:AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String
    ) = repo.login(emai = email, password = password)
}
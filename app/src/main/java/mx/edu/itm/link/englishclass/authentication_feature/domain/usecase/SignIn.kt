package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import javax.inject.Inject

class SignIn @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String
    ) = repo.login(emai = email, password = password)
}
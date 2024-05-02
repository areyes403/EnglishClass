package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase


import mx.edu.itm.link.englishclass.profile_feature.domain.model.User
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String,
        user: User
    ) = repo.register(email = email, password = password, user = user)
}
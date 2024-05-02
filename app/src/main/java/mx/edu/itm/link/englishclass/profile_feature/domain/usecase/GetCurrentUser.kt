package mx.edu.itm.link.englishclass.profile_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.profile_feature.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke() = repo.getUser()
}
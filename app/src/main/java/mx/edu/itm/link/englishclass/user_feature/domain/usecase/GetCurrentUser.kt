package mx.edu.itm.link.englishclass.user_feature.domain.usecase

import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke() = repo.getUser()
}
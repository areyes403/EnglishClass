package mx.edu.itm.link.englishclass.user_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repo: RoomRepository
) {
    suspend operator fun invoke() = repo.getUser()
}
package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import javax.inject.Inject

class GetLocalUser @Inject constructor(
    private val repo:RoomRepository
) {
    suspend operator fun invoke() = repo.getUser()
}
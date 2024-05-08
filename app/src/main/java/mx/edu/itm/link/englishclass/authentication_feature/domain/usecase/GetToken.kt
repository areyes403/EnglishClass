package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repo:AuthRepository
) {
    suspend operator fun invoke() = repo.getToken()
}
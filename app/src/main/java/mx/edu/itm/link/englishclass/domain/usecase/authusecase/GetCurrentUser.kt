package mx.edu.itm.link.englishclass.domain.usecase.authusecase

import mx.edu.itm.link.englishclass.domain.repository.AuthRepository

class GetCurrentUser(
    private val repo:AuthRepository
) {
    suspend operator fun invoke() = repo.getUser()
}
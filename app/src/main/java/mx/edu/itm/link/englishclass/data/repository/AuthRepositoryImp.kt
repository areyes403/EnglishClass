package mx.edu.itm.link.englishclass.data.repository

import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.domain.repository.AuthRepository

class AuthRepositoryImp : AuthRepository {
    override suspend fun login(emai: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun register(email: String, password: String, user: User) {
        TODO("Not yet implemented")
    }
}
package mx.edu.itm.link.englishclass.authentication_feature.domain.repository

import mx.edu.itm.link.englishclass.user_feature.domain.model.User

interface RoomRepository {
    suspend fun getUser(): User?
    suspend fun insertUser(user:User)
    suspend fun deleteUser(user: User)
}
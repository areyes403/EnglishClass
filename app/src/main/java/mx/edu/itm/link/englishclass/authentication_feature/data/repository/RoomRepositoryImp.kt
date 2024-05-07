package mx.edu.itm.link.englishclass.authentication_feature.data.repository

import android.util.Log
import mx.edu.itm.link.englishclass.authentication_feature.data.room.UserDao
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.core.utils.toUser
import mx.edu.itm.link.englishclass.core.utils.toUserEntity
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import javax.inject.Inject

class RoomRepositoryImp @Inject constructor(
    private val userDao: UserDao
) :RoomRepository {

    override suspend fun getUser(): User? = try {
        userDao.getAll()[0].toUser()
    }catch (e:Exception){
        null
    }

    override suspend fun insertUser(user: User) {
        userDao.insert(user = user.toUserEntity())
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}
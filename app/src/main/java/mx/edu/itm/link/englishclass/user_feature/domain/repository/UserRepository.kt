package mx.edu.itm.link.englishclass.user_feature.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User

interface UserRepository {
    suspend fun getUser(): FirebaseUser?
    suspend fun getActiveUsers(): Flow<ResponseStatus<List<User>>>
    suspend fun getRemoteToken(uid:String):ResponseStatus<String>
}
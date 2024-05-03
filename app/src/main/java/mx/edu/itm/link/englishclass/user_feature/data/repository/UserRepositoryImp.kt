package mx.edu.itm.link.englishclass.user_feature.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.UserCollection
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

class UserRepositoryImp @Inject constructor(
    private val authRef:FirebaseAuth,
    @UserCollection
    private val usersRef:CollectionReference
):UserRepository {
    override suspend fun getUser(): FirebaseUser? = authRef.currentUser


    override suspend fun getActiveUsers(): Flow<ResponseStatus<List<User>>> = usersRef
        .snapshots()
        .mapNotNull {
            ResponseStatus.Success(it.toObjects(User::class.java))
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }


}
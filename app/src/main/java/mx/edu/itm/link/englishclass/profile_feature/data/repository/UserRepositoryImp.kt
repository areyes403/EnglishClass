package mx.edu.itm.link.englishclass.profile_feature.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import mx.edu.itm.link.englishclass.profile_feature.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val authRef:FirebaseAuth
):UserRepository {
    override suspend fun getUser(): FirebaseUser? = authRef.currentUser
}
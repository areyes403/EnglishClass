package mx.edu.itm.link.englishclass.profile_feature.domain.repository

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun getUser(): FirebaseUser?
}
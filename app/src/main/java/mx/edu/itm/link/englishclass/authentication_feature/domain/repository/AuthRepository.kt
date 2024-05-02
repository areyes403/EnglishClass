package mx.edu.itm.link.englishclass.authentication_feature.domain.repository

import com.google.firebase.auth.FirebaseUser
import mx.edu.itm.link.englishclass.profile_feature.domain.model.User
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus

interface AuthRepository {
    suspend fun login (emai:String,password:String): ResponseStatus<String>
    suspend fun register (email:String , password: String , user: User): ResponseStatus<String>
}
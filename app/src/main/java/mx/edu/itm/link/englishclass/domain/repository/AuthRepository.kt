package mx.edu.itm.link.englishclass.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.domain.model.ResponseStatus

interface AuthRepository {
    suspend fun login (emai:String,password:String):ResponseStatus<String>
    suspend fun register (email:String , password: String , user: User):ResponseStatus<String>
    suspend fun getUser():FirebaseUser?
}
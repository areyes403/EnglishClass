package mx.edu.itm.link.englishclass.authentication_feature.domain.repository

import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus

interface AuthRepository {
    suspend fun login (emai:String,password:String): ResponseStatus<User>
    suspend fun register (email:String , password: String , user: User): ResponseStatus<User>
}
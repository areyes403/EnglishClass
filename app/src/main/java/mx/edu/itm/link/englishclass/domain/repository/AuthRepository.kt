package mx.edu.itm.link.englishclass.domain.repository

import mx.edu.itm.link.englishclass.data.model.User

interface AuthRepository {
    suspend fun login (emai:String,password:String)
    suspend fun register (email:String,password: String,user: User)
}
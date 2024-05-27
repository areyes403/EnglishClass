package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.model.Token
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class UpdateToken @Inject constructor(
    private val repo:UserRepository
){
    suspend operator fun invoke(
        idUser:String,
        myToken:Token
    ) = repo.updateRemoteToken(uid = idUser, token = myToken)
}
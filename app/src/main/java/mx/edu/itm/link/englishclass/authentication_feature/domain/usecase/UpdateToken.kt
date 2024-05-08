package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateToken @Inject constructor(
    private val repo:AuthRepository
){
    suspend operator fun invoke(
        idUser:String,
        myToken:String
    ) = repo.updateTokenToServer(id = idUser, token = myToken)
}